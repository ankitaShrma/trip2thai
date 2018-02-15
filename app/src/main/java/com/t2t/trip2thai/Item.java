package com.t2t.trip2thai;

/**
 * Created by pragati on 7/7/2016.
 */
public class Item {
    private int id;
    private int images;
    private String name;
    private String info;


    //Constructor
    public Item(int id, int images, String name, String info){
        this.id=id;
        this.images=images;
        this.name=name;
        this.info=info;
       // this.description=description;
    }

    //Setter and getter



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImages() {
        return images;
    }
    public void setImages(int images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

   // public String getDescription() {
   //     return description;
   // }

 //   public void setDescription(String description) {
    //    this.description = description;
   // }
}
