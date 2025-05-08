![image](https://github.com/user-attachments/assets/abcbd843-6cf2-4cd1-b3a8-25825734d8b6)**knight Board**

**Build the docker image**

	docker build -t knight_board:latest .

**Run the container**

    docker run \
    -e BOARD_API=https://storage.googleapis.com/jobrapido-backendtest/board.json \
    -e COMMANDS_API=https://storage.googleapis.com/jobrapido-backendtest/commands.json \
    knight_board:latest

(![image](https://github.com/user-attachments/assets/55671ef7-fa52-445f-aa6b-2b4ba4a73e54)

