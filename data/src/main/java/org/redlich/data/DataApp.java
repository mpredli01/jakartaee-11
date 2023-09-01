package org.redlich.data;

public class DataApp {
    public static void main(String[] args) {
        String title = "[APP] Welcome to the Jakarta Data Demo Application";
        displayTitle(title);
        }

    public static void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        }
    }
