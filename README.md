**knight Board**

**Build the docker image**

	docker build -t knight_board:latest .

**Run the container**

    docker run \
    -e BOARD_API=https://storage.googleapis.com/jobrapido-backendtest/board.json \
    -e COMMANDS_API=https://storage.googleapis.com/jobrapido-backendtest/commands.json \
    knight_board:latest

# Screenshot
https://github.com/ghayth-farhat99/knight_board/issues/1#issue-3049999960
