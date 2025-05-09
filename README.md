# Knight Board

A simple Java application simulating a knight navigating a board with obstacles.

## Components

- **Board**: Represents the game board with a given width, height, and list of obstacles.
- **Knight**: Represents a knight that can move and rotate on the board in four directions (NORTH, SOUTH, EAST, WEST).
- **Obstacle**: Represents an obstacle located at a specific (x, y) coordinate on the board.

## Build the Docker Image

```bash
docker build -t knight_board:latest .

```

## Run the Container
```bash
    docker run \
    -e BOARD_API=https://storage.googleapis.com/jobrapido-backendtest/board.json \
    -e COMMANDS_API=https://storage.googleapis.com/jobrapido-backendtest/commands.json \
    knight_board:latest
```

![image](https://github.com/user-attachments/assets/55671ef7-fa52-445f-aa6b-2b4ba4a73e54)

