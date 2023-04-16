package Tetris;

import java.io.*;
import java.util.Scanner;

public class Player {

    public int record;
    public int gamePLayed;
    public int score = 0;
    public String name;

    // This variable is used to know the line number of the player in the database
    private int numLine = 0;

    // Load a player data by searching it's name in the database.
    public Player(String name) {
        name = name.toLowerCase().strip();

        // try catch structure is needed since an error to open the database can occure
        try {
            Scanner sc = new Scanner(new File("./Tetris/players"));
            String line;
            while(sc.hasNextLine()){
                line = sc.nextLine();
                String[] tokens = line.split(";");

                // If the current line is not the data of the searched player, continue
                if (!tokens[0].split(":")[1].toLowerCase().equals(name.toLowerCase())){
                    this.numLine++;
                    continue;
                }
                
                for(String token : tokens){
                    String[] splitted = token.split(":");
                    switch(splitted[0]){
                        case "name":
                            this.name = splitted[1];
                            break;
                        case "record":
                            this.record = Integer.parseInt(splitted[1]);
                            break;
                        case "game_played":
                            this.gamePLayed = Integer.parseInt(splitted[1]);
                            break;
                    }
                }
                break;
            }
            if (this.name == null){
                this.name = name;
                this.gamePLayed = 0;
                this.record = 0;
                this.score = 0;
            }

            sc.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void checkRecord(int score) {
        if (score > this.record) {
            this.record = score;
        }
    }

    public void saveInfos(){

        String fileName = "./Tetris/players";
        record = score > record ? score : record;
        String newLine = "name:" + this.name + ";record:" + this.record + ";game_played:" + ++this.gamePLayed;

        try {
            File tempFile = new File("./temp");
            tempFile.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            for (int i = 0; i < this.numLine; i++) {
                line = reader.readLine();
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            writer.write(newLine);
            writer.newLine();
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();

            File oldFile = new File(fileName);
            oldFile.delete();
            tempFile.renameTo(oldFile);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
