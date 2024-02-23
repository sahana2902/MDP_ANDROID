package com.example.mdpandroidcontroller;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.mdpandroidcontroller.databinding.ActivityConnectBinding;
import com.example.mdpandroidcontroller.databinding.ActivityTempButtonsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempButtonsActivity extends DrawerBaseActivity {
    private static MapArena map;
    private static int[][] originalObstacleCoords = new int[20][2];
    private static int[] originalObstacleCoordinates2 = new int[2];
    // Key: osbtacleNumber; value: obstacle ID generated by View.generateViewId()
    private static Map<Integer, Integer> obstacleIds = new HashMap<>();
    private static int[][] currentObstacleCoords = new int[20][2]; // remember to expand this
    private static Map <Integer, int[]> latestObstacleCoordinates = new HashMap<>();

    // this one is for constraint
    private Map <Integer, ConstraintLayout> obstacleViews = new HashMap<>(); // cant be static!! - COS ITS REGENRATED ALL THE TIME - change eventually.
    private Map <Integer, ImageView> obstacleFaceViews2 = new HashMap<>();
    private Map <Integer, TextView> obstacleTextViews = new HashMap<>();
    private List<ImageView> obstacleBoxViews = new ArrayList<>();

    float pastX, pastY;
    ActivityTempButtonsBinding activityTempButtonsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTempButtonsBinding = activityTempButtonsBinding.inflate(getLayoutInflater());

        setContentView(activityTempButtonsBinding.getRoot());
        allocateActivityTitle("Temp Buttons");
//
//        TableLayout obstacleInformationTable = findViewById(R.id.obstacleInformation);
//        map = findViewById(R.id.mapView);
//        for (int i = 0; i < originalObstacleCoords.length; i++) {
//            for (int j = 0; j < originalObstacleCoords[i].length; j++) {
//                originalObstacleCoords[i][j] = -1;
//            }
//        }
//        Button preloadObstaclesButton = (Button) findViewById(R.id.preloadObstaclesButton);
//        Spinner spinner = findViewById(R.id.numberOfPreloadedObstacles);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this,
//                R.array.number_of_obstacles_to_preload,
//                android.R.layout.simple_spinner_item
//        );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        ViewGroup parentView = (ViewGroup) map.getParent();
//        preloadObstaclesButton.setOnClickListener(new View.OnClickListener() {
//                                                      @Override
//                                                      public void onClick(View view) {
//                                                          String userInput = spinner.getSelectedItem().toString();
//                                                          int numberOfObstacles = 0;
//                                                          try {
//                                                              numberOfObstacles = Integer.parseInt(userInput);}
//                                                          catch (NumberFormatException e) {
//                                                              numberOfObstacles = 0;
//                                                          }
//                                                          int mapCellSize = (int) map.getCellSize();
//                                                          int xMapCoordinate = 19;
//                                                          int yMapCoordinate = 1;
//                                                          for (int j = 0; j < numberOfObstacles; j++) {
//                                                              int newObstacleNumber = obstacleViews.size() + 1;
//                                                              while (obstacleViews.get(newObstacleNumber) != null) {
//                                                                  newObstacleNumber++;
//                                                              }
//                                                              ConstraintLayout newObstacle = createNewObstacle(newObstacleNumber);
//                                                              ConstraintLayout fullScreen = findViewById(R.id.fullScreen);
//                                                              ConstraintSet constraintSet = new ConstraintSet();
//                                                              constraintSet.clone(fullScreen);
//                                                              constraintSet.clear(newObstacle.getId(), ConstraintSet.START);
//                                                              constraintSet.clear(newObstacle.getId(), ConstraintSet.END);
//                                                              constraintSet.clear(newObstacle.getId(), ConstraintSet.TOP);
//                                                              constraintSet.clear(newObstacle.getId(), ConstraintSet.BOTTOM);
//                                                              constraintSet.connect(newObstacle.getId(), ConstraintSet.START, R.id.fullScreen, ConstraintSet.START);
//                                                              constraintSet.connect(newObstacle.getId(), ConstraintSet.TOP, R.id.fullScreen, ConstraintSet.TOP);
//                                                              // Apply the constraints to the parent ConstraintLayout
//                                                              constraintSet.applyTo(fullScreen);
//                                                              int xCoordinate = ((xMapCoordinate + 1) * mapCellSize) + (int) map.getX();
//                                                              int yCoordinate = ((19 - yMapCoordinate) * mapCellSize) + (int) map.getY();
//                                                              map.insertNewObstacleIntoArena(newObstacleNumber, xCoordinate, yCoordinate);
//                                                              latestObstacleCoordinates.put(newObstacleNumber, new int[] {xCoordinate, yCoordinate});
//                                                              newObstacle.setX(xCoordinate);
//                                                              newObstacle.setY(yCoordinate);
//                                                              map.generateObstacleInformationTableRows(obstacleInformationTable, obstacleViews, parentView, outputNotifView, latestObstacleCoordinates);
//                                                              map.invalidate();
//                                                              // Notification
//                                                              outputNotif = String.format("Obstacle: %d, Row: %d, Col: %d", newObstacleNumber, xMapCoordinate, yMapCoordinate);
//                                                              outputNotifView.setText(outputNotif);
//                    /*if (Constants.connected) {
//                        byte[] bytes = outputNotif.getBytes(Charset.defaultCharset());
//                        BluetoothChat.writeMsg(bytes);
//                    }*/
//                                                              yMapCoordinate += 2;
//                                                              // Check that popup still opens (if branch)
//                                                          }
//                                                      }
//                                                  }
      //  );
    }
//
//    private ConstraintLayout createNewObstacle(int obstacleNumber) {
//        System.out.println("Creating new obstacle...");
//        ConstraintLayout newObstacleGroup = new ConstraintLayout(this);
//        newObstacleGroup.setId(View.generateViewId());
//        obstacleIds.put(obstacleNumber, newObstacleGroup.getId());
//        newObstacleGroup.setTag(obstacleNumber);
//        int obstacleLength = (int) map.getCellSize();
//        System.out.println(String.format("Obstacle Length %d", obstacleLength));
//        newObstacleGroup.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT));
//        ImageView newObstacleBox = new ImageView(this);
//        newObstacleBox.setId(View.generateViewId());
//        newObstacleBox.setTag(obstacleNumber);
//        newObstacleBox.setImageResource(R.drawable.pink_box);
//        ConstraintLayout.LayoutParams boxParams = new ConstraintLayout.LayoutParams(obstacleLength, obstacleLength);
//        newObstacleBox.setLayoutParams(boxParams);
//        // Create the second ImageView
//        ImageView newObstacleFace = new ImageView(this);
//        newObstacleFace.setId(View.generateViewId()); // Generate a unique ID for the view
//        newObstacleFace.setTag(obstacleNumber);
//        newObstacleFace.setImageResource(R.drawable.pink_box);
//        ConstraintLayout.LayoutParams faceParams = new ConstraintLayout.LayoutParams(obstacleLength, obstacleLength);
//        newObstacleFace.setLayoutParams(faceParams);
//        newObstacleFace.setVisibility(View.INVISIBLE);
//        // Create the TextView
//        TextView newObstacleNumber = new TextView(this);
//        newObstacleNumber.setId(View.generateViewId()); // Generate a unique ID for the view
//        newObstacleNumber.setTag(String.format("Obstacle %d text view", obstacleNumber));
//        newObstacleNumber.setText(Integer.toString(obstacleNumber));
//        newObstacleNumber.setTextColor(Color.WHITE);
//        Typeface mainFont = Typeface.MONOSPACE;
//        newObstacleNumber.setTypeface(mainFont);
//        newObstacleNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        newObstacleNumber.setGravity(Gravity.CENTER_HORIZONTAL);
//        ConstraintLayout.LayoutParams idParams = new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        newObstacleNumber.setLayoutParams(idParams);
//        // Add the child views to the ConstraintLayout
//        newObstacleGroup.addView(newObstacleBox);
//        newObstacleGroup.addView(newObstacleFace);
//        newObstacleGroup.addView(newObstacleNumber);
//        // Set constraints for child views (if needed)
//        ConstraintSet constraintSet = new ConstraintSet();
//        constraintSet.clone(newObstacleGroup);
//        // Add constraints to new obstacle Face
//        constraintSet.connect(newObstacleFace.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
//        constraintSet.connect(newObstacleFace.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
//        // Add constraint to new obstacle box
//        constraintSet.connect(newObstacleBox.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
//        constraintSet.connect(newObstacleBox.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
//        // Add constraint to new obstacle number
//        constraintSet.connect(newObstacleNumber.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
//        constraintSet.connect(newObstacleNumber.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
//        constraintSet.connect(newObstacleNumber.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
//        constraintSet.connect(newObstacleNumber.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
//        constraintSet.centerVertically(newObstacleNumber.getId(), ConstraintSet.PARENT_ID); // Center vertically
//        constraintSet.centerHorizontally(newObstacleNumber.getId(), ConstraintSet.PARENT_ID); // Center horizontally
//        constraintSet.applyTo(newObstacleGroup);
//        // Add the child ConstraintLayout to its parent ConstraintLayout (assuming your parent ConstraintLayout has an ID)
//        ConstraintLayout fullScreen = findViewById(R.id.fullScreen);
//        fullScreen.addView(newObstacleGroup);
//        constraintSet.clone(fullScreen);
//        constraintSet.connect(newObstacleGroup.getId(), ConstraintSet.START, R.id.generator, ConstraintSet.START);
//        constraintSet.connect(newObstacleGroup.getId(), ConstraintSet.END, R.id.generator, ConstraintSet.END, 130);
//        constraintSet.connect(newObstacleGroup.getId(), ConstraintSet.TOP, R.id.mapView, ConstraintSet.BOTTOM, 10);
//        constraintSet.connect(newObstacleGroup.getId(), ConstraintSet.BOTTOM, R.id.generator, ConstraintSet.TOP);
//        // Apply the constraints to the parent ConstraintLayout
//        constraintSet.applyTo(fullScreen);
//        // Insert into lists
//        obstacleViews.put(obstacleNumber, newObstacleGroup);
//        obstacleFaceViews2.put(obstacleNumber, newObstacleFace);
//        obstacleTextViews.put(obstacleNumber, newObstacleNumber);
//        obstacleBoxViews.add(newObstacleBox);
//        newObstacleGroup.setOnTouchListener(obstacleOnTouchListener);
//        return newObstacleGroup;
//    }
//
//    private View.OnTouchListener obstacleOnTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                ClipData data = ClipData.newPlainText("", "");
//                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
//                view.startDrag(data, shadowBuilder, view, 0);
//                pastX = view.getX();
//                pastY = view.getY();
//                // return true;
//                // } else {
//                //    return false;
//                //}
//            }
//            return false;
//        }
//    };
}