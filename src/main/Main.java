/*
 * Main.java
 *
 * Version 1.0
 *
 * 2026-10-21
 *
 * LAB211 - Worker Management project.
 * This file starts the program.
 */

package main;

import controller.WorkerController;

/**
 * The starting point of the Worker Management program.
 *
 * <p>It only creates a {@link WorkerController} and runs it. All the real
 * work (menu, add worker, change salary...) is done in the controller.
 */
public class Main {

    /**
     * The program starts here.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Create the controller and start the menu.
        new WorkerController().run();
    }
}
