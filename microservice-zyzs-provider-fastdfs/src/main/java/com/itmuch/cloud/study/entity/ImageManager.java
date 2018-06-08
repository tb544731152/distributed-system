package com.itmuch.cloud.study.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "zyzs_image_manager")
public class ImageManager {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String imageName;
  @Column
  private String url;
  @Column
  private String senId;
  @Column
  private Date createDate;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSenId() {
    return senId;
  }

  public void setSenId(String senId) {
    this.senId = senId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
