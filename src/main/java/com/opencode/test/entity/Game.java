package com.opencode.test.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Game {

    public static final int SIZE_NUMBER = 4;

    private List<Byte> numbers = new ArrayList<Byte>();
    private byte bull;
    private byte cow;
    private List<Byte> playerNum = new ArrayList<Byte>();
    private List<String> history = new ArrayList<>();
    private int countStep = 0;
    private boolean endGame;

    public Game() {
        endGame = false;
        numbers = generateNumbers();
    }

    public List<Byte> getNumbers() {
        return numbers;
    }

    public byte getBull() {
        return bull;
    }

    public void setBull(byte bull) {
        this.bull = bull;
    }

    public byte getCow() {
        return cow;
    }

    public void setCow(byte cow) {
        this.cow = cow;
    }

    public String check(String playerNumber) {
        playerNum.clear();
        bull = 0;
        cow = 0;
        for (int n = 0; n < playerNumber.length(); n++) {
            playerNum.add((byte) (playerNumber.charAt(n) - '0'));
        }
        for (int i = 0; i < SIZE_NUMBER; i++) {
            for (int j = 0; j < SIZE_NUMBER; j++) {
                if (numbers.get(i) == playerNum.get(j)) {
                    if (i == j) bull += 1;
                    cow += 1;
                    break;
                }
            }
        }

        if (bull == 4) endGame = true;
        history.add(getResult());
        countStep++;
        return getResult();
    }

    private String getResult() {
        String s = "";
        s = playerNum.toString() + " # " + bull + " bull " + cow + " cow";
        return s;
    }

    public String getSecretNumbers() {
        return numbers.toString();
    }

    private List<Byte> generateNumbers() {
        List<Byte> gen = new ArrayList<Byte>();
        Random r = new Random(Calendar.getInstance().getTimeInMillis() - 31);
        byte n;
        for (int i = 0; i < SIZE_NUMBER; i++) {
            n = (byte) r.nextInt(10);
            if (!gen.contains(n)) {
                gen.add(n);
            } else {
                i--;
            }
        }
        return gen;
    }

    public void setNumbers(List<Byte> numbers) {
        this.numbers = numbers;
    }

    public List<Byte> getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(List<Byte> playerNum) {
        this.playerNum = playerNum;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public int getCountStep() {
        return countStep;
    }

    public void setCountStep(int countStep) {
        this.countStep = countStep;
    }

    public boolean isEndGame() {
        return endGame;
    }
}
