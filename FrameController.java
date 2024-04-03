package com.example;

import javax.swing.table.DefaultTableModel;

public class FrameController {

    private SparkPrelude sparkPrelude;
    private PageOne pageOne;
    private PrioPanel prioPanel;
    private SchedRoutineSummary schedRoutineSummary;

    public FrameController(){
        sparkPrelude = new SparkPrelude(this);
    }

    public void switchToPageOne(){
        if (pageOne == null){
            pageOne = new PageOne(this);
        }
        sparkPrelude.dispose();
    }

    public void switchToPrioPanel(){
        if (prioPanel == null){
            prioPanel = new PrioPanel(this, pageOne.getTaskStack());
        }
        pageOne.dispose();
    }

    public void switchToSchedRoutineSummary(DefaultTableModel model) {
        if (schedRoutineSummary == null) {
            schedRoutineSummary = new SchedRoutineSummary(this);
        }
        schedRoutineSummary.setDataTable(model);
        schedRoutineSummary.setVisible(true);
    }

    public void schedRoutineSummaryClosed(){
        // Reset SchedRoutineSummary instance when closed
        schedRoutineSummary = null;
    }

    public static void main(String[] args) {
        new FrameController();

    }
}
