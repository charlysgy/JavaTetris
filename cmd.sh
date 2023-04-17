CMD=$1
if [ "$CMD" = "compile" ]
then javac Tetris/*.java
elif [ "$CMD" = "run" ]
then java Tetris.GameEngine
else
    echo "Usage: cmd.sh [action]"
    echo "Actions:"
    echo -e "\tcompile: Compile the project"
    echo -e "\trun: Run the project"
fi