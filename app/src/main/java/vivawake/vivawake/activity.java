package vivawake.vivawake;

public class activity {
    private int hours;
    private int minutes;
    private String name;

    activity(){

    }

    activity(String names, int hour, int minute){
        this.hours = hour;
        this.name = names;
        this.minutes = minute;
    }

    public String getName(){
        return name;
    }

    public int getHour(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public String toString(){
        return "Name: " + name + " Hours: " + hours + " Minutes: " + minutes;
    }


}
