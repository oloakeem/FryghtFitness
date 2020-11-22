package come.example.myone;


import come.example.myone.ui.Array;

public class Practice {

    public static void main(String[] args) {

        Array array = new Array(5);
            array.insert(20);
            array.insert(33);
        array.insert(24);
        array.insert(35);
        array.insert(26);
        array.insert(37);
        array.removeAt(5);
            array.print();
        }
    }


