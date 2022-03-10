package com.example.tictactoegame;

public class BoardHandler {
    public char[] textView;

    public BoardHandler(){
        // textViews=new char[9];
        textView=new char[]{'E','E','E','E','E','E','E','E','E'};
    }

    public char[] getTextView() {
        return textView;
    }

    public void setTextView(char[] textView) {
        this.textView = textView;
    }

    public char getTextView(int i) {
        return textView[i];
    }

    public void setTextView(char value,int i) {
        this.textView[i] = value;
    }

    public char checkWinner()
    {
             if(textView[0]==textView[1] && textView[0]==textView[2] && textView[0]!='E') return textView[0];
        else if(textView[3]==textView[4] && textView[3]==textView[5] && textView[3]!='E') return textView[3];
        else if(textView[6]==textView[7] && textView[6]==textView[8] && textView[6]!='E') return textView[6];
        else if(textView[0]==textView[3] && textView[0]==textView[6] && textView[0]!='E') return textView[0];
        else if(textView[1]==textView[4] && textView[1]==textView[7] && textView[1]!='E') return textView[1];
        else if(textView[2]==textView[5] && textView[2]==textView[8] && textView[2]!='E') return textView[2];
        else if(textView[2]==textView[4] && textView[2]==textView[6] && textView[2]!='E') return textView[2];
        else if(textView[8]==textView[4] && textView[8]==textView[0] && textView[8]!='E') return textView[8];
        else  return 'E';
    }
    public boolean boardIsFull()
    {
        for(int i=0;i<9;i++)
        {if(textView[i]=='E')return false;
        }
        return true;
    }

    public void resetBoard(char[] textViews)
    {
        this.textView=new char[]{'E','E','E','E','E','E','E','E','E'};
    }

}
