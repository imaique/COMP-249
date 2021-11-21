package part2;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
// -----------------------------------------------------
// Part: 2
// Written by: Michael Jr Osuji 40182642
// -----------------------------------------------------
public class ShowList {
    private ShowNode head;
    private int size;

    public Show getShow(String ShowID) {
        return find(ShowID).show;

    }

    /**
     * Default constructor for ShowList. Sets head to null
     */
    public ShowList(){
        size = 0;
        head = null;
    }

    public ShowNode getHead() {
        return head;
    }

    /**
     * Adds the given show to the start of the list
     * @param object the show to be added
     */
    public void addToStart(Show object){
        ShowNode temp = new ShowNode(object, head);
        head = temp;
        size++;
    }

    /**
     * Is equal if both lists are of the same size and contain the same shows, regardless of order.
     * @param list the showlist that the calling showlist wants to compare to
     * @return true or false.
     */
    public boolean equals(ShowList list){
        if (list.size!=size) return false;
        ShowNode call = head;
        ShowNode compared = list.head;
        for (int i =0; i < size ; i++){
            if (!list.hasShow(call.show)){
                return false;
            }
            call = call.pointer;
        }
        return true;
    }

    /**
     * Method that finds whether this list has a certain show
     * @param show the show to be found
     * @return whether the list contains the show or not
     */
    public boolean hasShow(Show show){
        ShowNode temp = head;
        while(temp!=null){
            if (temp.show.equals(show))
                return true;
            temp = temp.pointer;
        }
        return false;
    }

    /**
     * Inserts a show at a given index
     * @param show show to be inserted
     * @param index index at which the show needs to be inserted
     * @throws NoSuchElementException
     */
    public void insertAtIndex(Show show, int index) throws NoSuchElementException{
        if (index>size||index<0) throw new NoSuchElementException();
        if (index==0) addToStart(show);
        else {
            ShowNode temp = nodeAtIndex(index-1);
            ShowNode insert = new ShowNode(show,temp.pointer);
            temp.pointer = insert;
            size++;
        }

    }

    /**
     * Deletes the node at the given index
     * @param index index of the node to be deleted
     */
    public void deleteFromIndex(int index){
        if (index>size||index<0) throw new NoSuchElementException();
        ShowNode temp = nodeAtIndex(index-1);
        temp.pointer = temp.pointer.pointer;
        size--;
    }

    public void printList(){
        ShowNode temp= head;
        int index = 1;
        while(temp!=null){
            System.out.println("(" + index + ") " + temp.show);
            temp = temp.pointer;
            index++;
        }
    }
    /**
     * Deletes the head of the list
     */
    public void deleteFromStart(){
        if (size>0) {
            head = head.pointer;
            size--;
        }
        else throw new NoSuchElementException();
    }

    /**
     * Replaces the show at a certain index
     * @param object show that will replace the old show
     * @param index index of the show to be replaced
     */
    public void replaceAtIndex(Show object, int index){
        if (index>size-1||index<0) return;
        ShowNode temp = nodeAtIndex(index-1);
        ShowNode newShow = new ShowNode(object,temp.pointer.pointer);
        temp.pointer = newShow;
    }

    /**
     * Finds a certain node and returns it. Also prints the amount of times it needed to iterate to find the object.
     * @param showID the showId of the node to be found
     * @return the first node with that showID
     */
    public ShowNode find(String showID){
        ShowNode check = head;
        for (int i = 0; i < size; i++ ){
            if (check.show.getShowID().equalsIgnoreCase(showID)){
                System.out.println("find() method iterated " + (i+1) + " times.");;
                return check;
            }
            check = check.pointer;
        }
        System.out.println("find() method iterated " + size + " times. No object found");;
        return null;
    }

    /**
     * Returns whether the liist contains a certain show with a certain showID or not
     * @param showID
     * @return
     */
    public boolean contains(String showID){
        ShowNode check = head;
        for (int i = 0; i < size; i++ ){
            if (check.show.getShowID().equals(showID)){
                return true;
            }
            check = check.pointer;
        }
        return false;
    }

    /**
     * clones the node at the index and adds the clone after the original
     * @param index index of the desired
     * @throws CloneNotSupportedException
     */
    public void cloneAtIndex(int index) throws CloneNotSupportedException {
        if (index>size-1||index<0) return;
        ShowNode temp = nodeAtIndex(index-1);
        ShowNode temp2 = (ShowNode) temp.clone();
        temp.pointer = temp2;
    }


    /**
     * Prints whether the calling list of shows that the user will watch allows for the shows in the passing list to be watched.
     * @param wish list of shows that the user wishes to watch.
     */
    public void printWatchable(ShowList wish){

        ShowNode tempWish = wish.head;
        ShowNode temp = head;

        while(tempWish!=null){
            while (temp!=null){


                String sameTime = tempWish.show.isOnSameTime(temp.show);



                if (sameTime.equalsIgnoreCase("Some Overlap")){
                    System.out.println("User can't watch show " + tempWish.show.getShowID() + " as he is not finished with a show he/she is watching.");
                    break;
                }
                if (sameTime.equalsIgnoreCase("same time")){
                    System.out.println("User can't watch show " + tempWish.show.getShowID() + " as he/she will begin another show at the same time.");
                    break;
                }

                if (temp.pointer==null){
                    System.out.println("User can watch show " + tempWish.show.getShowID() + " as he/she is not watching anything else during that time.");
                }
                temp = temp.pointer;

            }
            temp = head;
            tempWish= tempWish.pointer;
        }

    }

    /**
     * Inner method to return the node at a certain index (similar to an iterator)
     * @param index index where the node will be returned
     * @return node
     */
    public ShowNode nodeAtIndex( int index){
        ShowNode pass = head;
        for (int i = 0; i<index-1; i++){
            pass = pass.pointer;
        }
        return pass;

    }


    /**
     * Private inner class that contains the next ShowNode and the show of the current node
     */
    private class ShowNode implements Cloneable{
        private Show show;
        private ShowNode pointer;

        public ShowNode(){
            this.show=null;
            this.pointer=null;
        }

        public ShowNode(Show show, ShowNode pointer){
            this.show = show;
            this.pointer = pointer;
        }

        /**
         * Deep copy of the shownode object.
         * @param showNode
         */
        public ShowNode(ShowNode showNode){

            this.show = new Show(showNode.show,showNode.show.getShowID());
            if (showNode.pointer==null){
                this.pointer= null;
            }
            else {
                this.pointer = new ShowNode(showNode.pointer);
            }
        }

        /**
         * Deep Copy of the ShowNode
         * @return Clone of the ShowNode
         * @throws CloneNotSupportedException
         */
        public Object clone() throws CloneNotSupportedException {
            ShowNode clone = (ShowNode)super.clone();

            clone.show = new Show(show,show.getShowID());
            clone.pointer = new ShowNode(pointer);
            return clone;
        }


    }
}
