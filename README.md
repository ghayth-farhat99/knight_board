# knight Board

## Build the Docker Image

	docker build -t knight_board:latest .

## Run the Container

    docker run \
    -e BOARD_API=https://storage.googleapis.com/jobrapido-backendtest/board.json \
    -e COMMANDS_API=https://storage.googleapis.com/jobrapido-backendtest/commands.json \
    knight_board:latest

(![image](https://github.com/user-attachments/assets/55671ef7-fa52-445f-aa6b-2b4ba4a73e54)

