package Laba7;

import java.io.Serializable;

public class Hotel implements Serializable {
    private static int counter = 0;
    private int id;
    private String name;
    private String date1;
    private String date2;
    private String room;
    private String reason;


    public static void cleanVacancy(){
        counter = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }


    public int getId() {
        return id;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("-----------------------------------------\n");
        str.append("ID: ").append(id).append("\n");
        str.append("Паспортні дані: ").append(name).append("\n");
        str.append("Дата заселення: ").append(date1).append("\n");
        str.append("Дата виселення: ").append(date2).append("\n");
        str.append("Кімната: ").append(room).append("\n");
        str.append("Причина заселення: ").append(reason).append("\n");

        str.append("-----------------------------------------");
        return str.toString();
    }
}