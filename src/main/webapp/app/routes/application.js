export default Ember.Route.extend({
  setupController(controller, model) {
    console.debug('in Route:Application setupController');

    $(function() {
      $('#m-add-files-btn').leanModal({
        //dismissible: true, // Modal can be dismissed by clicking outside of the modal
        //opacity: .5, // Opacity of modal background
        //in_duration: 300, // Transition in duration
        //out_duration: 200, // Transition out duration
        //ready: function() { alert('Ready'); }, // Callback for Modal open
        //complete: function() { alert('Closed'); } // Callback for Modal close
      });

      //$('.file-field div.btn').on('click', function(e) {
      //  console.debug('# click');
      //  $(this).siblings('input').click();
      //});

      // copied from materializecss/js/forms.js
      $('.file-field').each(function() {
        var path_input = $(this).find('input.file-path');
        $(this).find('input[type="file"]').change(function () {
          var files = this.files,
            names = [];
          for (var i = 0, l = files.length; i < l; ++i) {
            names.push(files[i].name);
          }

          path_input.val(names.join(', '));
          path_input.trigger('change');
        });
      });

      //
      $('.tooltipped').tooltip({delay: 50});

      //
      controller.myInit();
    });

  }
});
