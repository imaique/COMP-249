package part2;

import java.util.Objects;
// -----------------------------------------------------
// Part: 2
// Written by: Michael Jr Osuji 40182642
// -----------------------------------------------------
public class Show implements Watchable, Cloneable {
    private String showID;
    private String showName;
    private double startTime;
    private double endTime;

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public String getShowID() {
        return showID;
    }

    public String getShowName() {
        return showName;
    }

    /**
     * Parametirized construction of Show
     * @param showID the showID
     * @param showName the name of the show
     * @param startTime the start time in double
     * @param endTime the end time in double
     */
    public Show(String showID, String showName, double startTime, double endTime){
        this.showID= showID;
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**
     * Copy constructor for Show
     * @param show The show to be copied
     * @param showID the new showID
     */
    public Show(Show show, String showID){
        this.showID= showID;
        this.showName = show.showName;
        this.startTime = show.startTime;
        this.endTime = show.endTime;
    }

    /**
     * Two shows are equal if everything but their IDs is the same.
     * @param o object to be compared to the calling object
     * @return boolean of wheter theyre equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return Double.compare(show.startTime, startTime) == 0 &&
                Double.compare(show.endTime, endTime) == 0 &&
                Objects.equals(showName, show.showName);
    }

    /**
     * Standard toString method that clearly describes the attributes and their contents.
     * @return the string.
     */
    @Override
    public String toString() {
        return "TVShow{" +
                "showID='" + showID + '\'' +
                ", showName='" + showName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    /**
     * isOnSameTime returns a string that describe the relationship between the times of each shows.
     * @param S the show the calling object wants to campre itself to.
     * @return a string that describes information about wheter or not their time overlap.
     */
    @Override
    public String isOnSameTime(Show S) {
        if (startTime==S.startTime){
            return "Same Time";
        }
        if (startTime<S.startTime){
            if (endTime<=S.endTime && S.startTime<endTime){
                return "Some Overlap";
            }
            return "Different Time";
        }
        //ABC start time CSB S
        if (S.startTime<startTime){
            if (S.endTime<=endTime && startTime<S.endTime){
                return "Some Overlap";
            }
            return "Different Time";
        }
        return "Impossible";
    }

    /**
     * deep copy cloned
     * @return cloned object
     * @throws CloneNotSupportedException
     */
    public Object clone(String showID) throws CloneNotSupportedException {
        Show clone = new Show(this,showID);

        return clone;
    }
}
