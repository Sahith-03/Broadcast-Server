# Spring Boot Real-Time Broadcast Server

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-Supported-blue?style=for-the-badge&logo=databricks&logoColor=white)

A simple yet powerful real-time broadcast server built with Spring Boot and WebSockets. This project serves as a foundational example for applications requiring real-time communication, such as chat rooms, live notifications, or collaborative tools.

When a message is sent from any connected client, the server instantly "broadcasts" it to all other connected clients.

## Features

- **Real-Time Communication:** Utilizes WebSockets for persistent, low-latency, bidirectional connections.
- **Multi-Client Broadcasting:** A message from one client is efficiently distributed to all other clients.
- **Connection Management:** Gracefully handles new client connections and disconnections.
- **Simple Frontend:** A clean, dependency-free HTML and JavaScript client to demonstrate functionality.
- **Scalable Foundation:** The core logic is a building block for more complex features like chat rooms or user-specific messaging.

## Tech Stack

- **Backend:**
    - Java 17
    - Spring Boot 3
    - Spring WebSocket
- **Build Tool:**
    - Apache Maven
- **Frontend:**
    - HTML5
    - CSS3
    - Vanilla JavaScript

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing.

### Prerequisites

Make sure you have the following installed on your system:
- **Java Development Kit (JDK)**: Version 17 or newer.
- **Apache Maven**: Version 3.8 or newer.

### Installation & Running the Application

1.  **Clone the repository (IMPORTANT: update this URL with your own):**
    ```sh
    git clone https://github.com/[YOUR GITHUB USERNAME]/[YOUR REPOSITORY NAME].git
    ```

2.  **Navigate to the project directory:**
    ```sh
    cd [YOUR REPOSITORY NAME]
    ```

3.  **Run the Spring Boot application using Maven:**
    This command will start the backend server on `http://localhost:8080`.
    ```sh
    ./mvnw spring-boot:run
    ```
    (On Windows, use `mvnw.cmd spring-boot:run`)

4.  **Test the application:**
    - Open your web browser and navigate to `http://localhost:8080`.
    - To see the broadcast in action, open **at least two browser tabs or windows** to the same address.
    - Type a message in one window and hit "Send". The message will appear instantly in all open windows.

## How It Works

### Backend (`Spring Boot`)

- **`WebSocketConfig.java`**: This configuration class enables WebSocket support (`@EnableWebSocket`) and registers our custom handler to a specific endpoint (`/broadcast`).
- **`BroadcastSocketHandler.java`**: This is the core logic.
    - It extends `TextWebSocketHandler`.
    - A thread-safe `CopyOnWriteArraySet<WebSocketSession>` is used to keep track of all active client sessions. This collection is efficient for scenarios where reads (broadcasts) are far more frequent than writes (connections/disconnections).
    - `afterConnectionEstablished()`: Adds a new client's session to the set.
    - `handleTextMessage()`: When a message is received from one client, it iterates through every session in the set and sends the message to each one.
    - `afterConnectionClosed()`: Removes a client's session from the set when they disconnect.

### Frontend (`HTML/JavaScript`)

- A single `index.html` file located in `src/main/resources/static` is served by Spring Boot.
- **Connection:** JavaScript establishes a WebSocket connection to the server using `new WebSocket('ws://localhost:8080/broadcast')`.
- **Receiving Messages:** The `socket.onmessage` event listener triggers whenever the server sends a message. It then creates a new HTML element and appends it to the message container.
- **Sending Messages:** An event listener on the "Send" button calls a function that uses `socket.send()` to transmit the input field's content to the server.

### Project URL 
https://roadmap.sh/projects/broadcast-server

## Future Enhancements

This project can be extended with many features. Here are a few ideas:

- [ ] **User Identification:** Assign usernames to clients and display who sent each message (e.g., "Alice: Hello world!").
- [ ] **Chat Rooms:** Allow clients to join specific channels and only receive messages from that channel.
- [ ] **Structured Messages (JSON):** Send JSON objects instead of plain text to include metadata like timestamps, sender, or message type.
- [ ] **Message Persistence:** Store messages in a database (like H2, PostgreSQL) so chat history is not lost when the server restarts.
- [ ] **User Authentication:** Integrate Spring Security to ensure only logged-in users can connect to the WebSocket.
