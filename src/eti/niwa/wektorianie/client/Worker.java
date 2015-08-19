package eti.niwa.wektorianie.client;

/**
 * Created by maciek on 18.08.15.
 */
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import eti.niwa.wektorianie.client.soap.SVGService;
import eti.niwa.wektorianie.client.soap.SVGServiceService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Worker implements Runnable {

  private BlockingQueue<UploadedImage> queue;
  private ConcurrentHashMap<Integer, ProcessedImage> map;
  private SVGService service;

  public Worker(BlockingQueue<UploadedImage> queue, ConcurrentHashMap<Integer, ProcessedImage> map) {
    this.queue = queue;
    this.map = map;
    this.service = new SVGServiceService().getSVGServicePort();
  }

  @Override
  public void run() {
      while (true) {
        try {
          UploadedImage image = queue.take();
          String result = service.processPicture(image.getData());
          //@todo: handle processing time
          ProcessedImage postImage = new ProcessedImage(1, result);

          map.put(image.getId(), postImage);
        } catch (InterruptedException | ServerSOAPFaultException ex) {
          Logger logger = Logger.getLogger(getClass().getName());
          logger.severe("No connection with web service");
        }
      }
  }
}
