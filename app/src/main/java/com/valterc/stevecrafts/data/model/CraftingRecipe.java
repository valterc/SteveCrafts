package com.valterc.stevecrafts.data.model;

/**
 * Created by Valter on 09/01/2015.
 */
public class CraftingRecipe {

    private String id;

    /**
     * 0 - Block; 1 - Item
     */
    private int type;
    private String craftId;
    private int count;
    private Slot[] slots;
    private long timestamp;

    public static class Slot{

        private String id;
        private int type;
        private int count;

        public Slot(String id, int type, int count){
            this.setId(id);
            this.setType(type);
            this.setCount(count);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * 0 - Block; 1 - Item
         */
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}
