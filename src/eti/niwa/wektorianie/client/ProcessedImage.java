package eti.niwa.wektorianie.client;

/**
 * Created by maciek on 18.08.15.
 */
public class ProcessedImage {
  private Integer processingTime;
  private String svg;

  public ProcessedImage(Integer processingTime, String svg) {
    this.processingTime = processingTime;
    this.svg = svg;
  }

  public Integer getProcessingTime() {
    return processingTime;
  }

  public void setProcessingTime(Integer processingTime) {
    this.processingTime = processingTime;
  }

  public String getSvg() {
    return svg;
  }

  public void setSvg(String svg) {
    this.svg = svg;
  }
}
