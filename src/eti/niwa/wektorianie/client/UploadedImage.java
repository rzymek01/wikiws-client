package eti.niwa.wektorianie.client;

/**
 * Created by maciek on 18.08.15.
 */
public class UploadedImage {
  private Integer id;
  private byte[] data;

  public UploadedImage(Integer id, byte[] data) {
    this.id = id;
    this.data = data;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
}
