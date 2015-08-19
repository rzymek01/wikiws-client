var it = 0;
const imageType = /^image\//;

var sendImage = function (cid, file, deferred) {
  var formData = new FormData();
  formData.append('file', file, file.name),

  $.ajax({
    'url': 'rest/process-picture',
    'type': 'POST',
    'data': formData,
    'contentType': false,
    'processData': false,
    'xhr': function () {
      var xhr = $.ajaxSettings.xhr();
      if (xhr.upload) {
        xhr.upload.addEventListener('progress', function () {
          console.debug('progress of', cid, arguments);
        }, false);
        xhr.upload.addEventListener('load', function () {
          console.debug('loaded', cid, arguments);
        }, false);
      }
      return xhr;
    },
    context: this
  }).done(function(response) {
    console.debug('success');
    this.incrementProperty('countUploaded');

    var images = this.get('imagesMap'),
      sid = +response;
    Ember.set(images[cid], 'sid', sid);
  }).fail(function() {
    console.warn('failure');
    var images = this.get('imagesMap');
    Ember.set(images[cid], 'error', true);
  }).always(function() {
    var images = this.get('imagesMap');
    images[cid].uploadingEndTime = new Date();
    Ember.set(images[cid], 'uploadingTime', images[cid].uploadingEndTime - images[cid].uploadingStartTime);
    deferred.resolve();
  });
  this.incrementProperty('countAdded');
};

var checkImage = function (sid, cid, deferred) {
  sid = +sid;
  cid = +cid;

  $.ajax({
    'url': 'rest/svg/' + sid + '/is-ready',
    'type': 'GET',
    'processData': false,
    context: this
  }).done(function(response) {
    console.debug('checkImage: success');

    var processingTime = +response;
    if (isNaN(processingTime) || 0 >= processingTime) {
      // svg is still not ready
      return;
    }

    this.incrementProperty('countProcessed');

    var images = this.get('imagesMap'),
      img = document.createElement("img");
    Ember.set(images[cid], 'dstImg', img);
    Ember.set(images[cid], 'processingTime', processingTime);

    img.src = 'rest/svg/' + sid + '.svg';

  }).fail(function() {
    console.warn('checkImage: failure');
    var images = this.get('imagesMap');
    Ember.set(images[cid], 'error', true);
  }).always(function() {
    deferred.resolve();
  });
};

var lockFilesUI = function() {
  console.debug('locking files ui...');
  let btn = getSubmitBtn();
  btn.prop('disabled', true);
  getLoadingBox().addClass('active');
};
var releaseFilesUI = function() {
  console.debug('releasing files ui...');
  let btn = getSubmitBtn();
  getForm().reset();
  getLoadingBox().removeClass('active');
  btn.prop('disabled', false);
};
var hideFilesUI = function() {
  console.debug('hiding files ui...');
  getCancelBtn().click();
};

var getByJQuerySelector = function(selector) {
  var dom = null;
  return function() {
    if (null === dom) {
      dom = $(selector);
    }
    return dom;
  }
};
var getFilesBox = getByJQuerySelector('#m-add-files-box');
var getInputFile = getByJQuerySelector('#m-add-files-box input[type=file]');
var getSubmitBtn = getByJQuerySelector('#m-add-files-box button');
var getCancelBtn = getByJQuerySelector('#m-add-files-box a.modal-close');
var getLoadingBox = getByJQuerySelector('#m-add-files-box .preloader-wrapper');
var getForm = function() {
  return getFilesBox().find('form')[0];
};

var checkUploadedImages = function() {
  var that = this,
    deferreds = [],
    deferred,
    images = this.get('imagesMap');

  for (let idx in images) {
    let image = images[idx];
    if (null !== image.processingTime) {
      continue;
    }

    deferred = $.Deferred();
    checkImage.call(this, image.sid, image.cid, deferred);
    deferreds.push(deferred);
  }

  $.when.apply($, deferreds).then(function() {
    setTimeout(function () {
      checkUploadedImages.call(that);
    }, 2000);
  });
};

/**
 *
 */
export default Ember.Controller.extend({
  images: null,
  imagesMap: {},
  //token: null,
  countAdded: 0,
  countUploaded: 0,
  countProcessed: 0,
  myInit: function() {
    console.debug('in init...');
    checkUploadedImages.call(this);

    var that = this;
    $('ul.m-image-ul').on('click', '.m-image-el-btn', function() {
      let cid = $(this).data('cid');
      let images = that.get('imagesMap');
      let image = images[cid] || null;

      if (null !== image) {
        let cntr = $('#m-left-image > div');
        cntr.empty();
        cntr.append(image.orgImg);

        cntr = $('#m-right-image > div');
        cntr.empty();
        cntr.append(image.dstImg);
      }
    });
  },

  actions: {
    addFiles() {
      let files = getInputFile()[0].files,
        l = files.length,
        images = this.get('images'),
        imagesMap = this.get('imagesMap'),
        deferreds = [],
        deferred,
        obj;
      if (!l) {
        return;
      }
      if (null == images) {
        images = [];
        this.set('images', images);
        //@todo: delete this
        window.__images = images;
      }
      lockFilesUI();

      for (let i = 0; i < l; ++i) {
        let cid = ++it,
          file = files[i],
          img, reader;

        if (!imageType.test(file.type)) {
          console.warn('bad mimetype of given file');
          continue;
        }

        img = document.createElement("img");
        reader = new FileReader();
        reader.onload = (function (aImg) {
          return function (e) {
            aImg.src = e.target.result;
          };
        })(img);
        reader.readAsDataURL(file);

        obj = {
          cid: cid,
          orgImg: img,
          sid: null,
          dstImg: null,
          name: file.name,
          uploadingStartTime: new Date(),
          uploadingEndTime: null,
          //processingEndTime: null,
          uploadingTime: null,
          processingTime: null,
          error: false
        };
        images.pushObject(obj);
        imagesMap[cid] = obj;

        deferred = $.Deferred();
        sendImage.call(this, cid, file, deferred);
        deferreds.push(deferred);
      }

      //UX: wait at least 600ms until release UI
      deferred = $.Deferred();
      deferreds.push(deferred);
      setTimeout(function() {
        deferred.resolve();
      }, 600);

      $.when.apply($, deferreds).then(releaseFilesUI).then(hideFilesUI);
    }
  }

});
