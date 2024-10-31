package com.eightpuzzle.graficInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eightpuzzle.BreadthSearch;
import com.eightpuzzle.Node;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EightPuzzleInterface extends Application{

    private final int[][] goal = {{1,2,3}, {4,5,6}, {7,8,0}};

    private Button[][] buttons = new Button[3][3];
    private int[][] board = new int[3][3];
    private Button breadthButton = new Button("Busca em Largura");
    private Button depthButton = new Button("Busca em Profundidade");
    private Button aStar = new Button("A-Star");
    private int emptyRow, emptyCol;

    @Override
    public void start(@SuppressWarnings("exports") Stage primayStage) throws Exception {
        primayStage.setTitle("8 Puzzle Game");

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        grid.setLayoutX(200);
        grid.setLayoutY(50);
        breadthButton.setLayoutY(50);
        depthButton.setLayoutY(100);
        aStar.setLayoutY(150);
        aStar.setPrefWidth(135);

        Pane root = new Pane();
        VBox vertical = new VBox(40);
        vertical.getChildren().add(breadthButton);
        vertical.getChildren().add(depthButton);
        vertical.getChildren().add(aStar);
        vertical.setLayoutY(75);
        vertical.setLayoutX(20);
        vertical.setAlignment(Pos.CENTER);
        
        root.getChildren().add(vertical);
        root.getChildren().add(grid);


        initializeBoard();
        createButtons(grid);
        Scene scene = new Scene(root, 500, 400);
        primayStage.setScene(scene);
        primayStage.show();
        
    }

    private void createButtons(GridPane grid) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button button = new Button();
                button.setPrefSize(80, 80);

                int value = board[i][j];

                if(value != 0){
                    button.setText(String.valueOf(value));
                }else{
                    button.setText("");
                    emptyRow = i;
                    emptyCol = j;
                }

                int row = i;
                int col = j;

                breadthButton.setOnAction(e -> breadthSearch());
                button.setOnAction(e -> setButtonValue(row, col));
                buttons[i][j] = button;
                grid.add(button, j, i);
                System.out.println("Passou");
            }
        }
    }

    private void breadthSearch() {
        BreadthSearch breadthSearch = new BreadthSearch();
        Node node = new Node(board, emptyRow, emptyCol);
        Node nodeTarget = new Node(goal, 3, 3);

        while(!node.equals(nodeTarget)){
            node = breadthSearch.breadthSearchAlgorithm(board, goal);
            board = node.getMatrix();
        }
        
    }

    private void setButtonValue(int row, int col) {
        if(board[row][col] + 1 == 9){
            board[row][col] = 0;
            buttons[row][col].setText("");
        }else{
            board[row][col] = board[row][col] + 1;
            buttons[row][col].setText(String.valueOf(board[row][col]));
        }
    }

    public void moveTile(int row, int col) {
        if((Math.abs(emptyRow - row) == 1 && emptyCol == col) || (Math.abs(emptyCol - col) == 1 && emptyRow == row)){
            board[emptyRow][emptyCol] = board[row][col];
            board[row][col] = 0;
            buttons[emptyRow][emptyCol].setText(buttons[row][col].getText());
            buttons[row][col].setText("");
            emptyCol = col;
            emptyRow = row;

            if(checkWin()){
                System.out.println("You Win!");
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] != goal[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    public void initializeBoard() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = numbers.get(count);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        try {
           launch(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
