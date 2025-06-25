# Collaborative Graphical Editor

This project implements a collaborative graphical editor inspired by Google Docs, where multiple users can simultaneously edit a shared sketch. The editor supports drawing and manipulating multiple shapes including rectangles, ellipses, line segments, and freehand polylines.

---

## Project Overview

- **Editor**: The client application with GUI allowing users to draw and edit shapes.
- **SketchServer**: The central server maintaining the master copy of the sketch and synchronizing changes across connected clients.
- **EditorCommunicator**: Handles communication between the Editor client and the SketchServer.
- **SketchServerCommunicator**: Handles communication between the server and a single Editor client.
- **Shape Interface & Implementations**: Abstract representation of graphical shapes with concrete shapes like Ellipse, Rectangle, Segment, and Polyline.

---

## Features

- Collaborative editing with multiple clients connected to the server.
- Supports multiple shape types:
  - Ellipse
  - Rectangle
  - Line Segment
  - Freehand Polyline
- All editing actions (add, delete, move, recolor) are submitted to the server and synchronized among all clients.
- Each shape is assigned a unique global ID by the server to maintain consistency.
- Partial shapes being drawn are handled locally and added to the sketch only when completed.
- Robust concurrency via synchronized sketch access on the server.
- Simple, human-readable message protocol used for client-server communication.

---

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- An IDE like IntelliJ IDEA or Eclipse (recommended for easier running multiple client instances)

### Download and Setup

1. Download the `editor.zip` scaffold containing all source files.
2. Extract the contents to your project directory.
3. Import the project to your IDE or compile using command line.

### Running the Server

- Run the `SketchServer` main class to start the collaborative sketch server.
- Alternatively, run `EchoServer` if you want a simple echo server for development/debugging.

### Running the Clients

- Run the `Editor` main class to start a client editor GUI.
- To test collaboration, run multiple instances of `Editor`.
  - **Note for IntelliJ**:  
    Go to `Run -> Edit Configurations...`  
    Select the `Editor` configuration and click `Modify options`  
    Check `Allow multiple instances`.

---

## How It Works

- **Client Actions**: The client editor GUI captures user drawing and editing actions.
- **Requests to Server**: Clients send requests to the server to add, delete, move, or recolor shapes.
- **Server Updates**: The server assigns shape IDs and updates the master sketch.
- **Broadcast**: Updates are broadcast to all clients, who apply changes in the same order.
- **Message Protocol**: Communication uses simple space/comma-separated string messages.
- **Synchronization**: The server synchronizes state and maintains consistency in a multi-threaded environment.
- **Partial Shapes**: Drawing partial shapes (like dragging) handled locally; only finalized shapes are added to the sketch.

---

## Development Notes

- Start by reviewing `EditorOne` to understand the single-shape client sample.
- Implement message-passing protocols with clear command formats.
- Use synchronized methods for accessing shared sketch data on the server.
- Assign unique IDs for shapes on the server side only.
- Handle different shape types appropriately in the editor (different drawing behaviors).
- Use `TreeMap` or similar for ordered shape storage and access by ID.
- Make sure all string comparisons use `.equals` method.
- Use RGB integer values for colors in message passing.
- Test with `EchoServer` before implementing full server logic.

---

## Useful Tips

- The current partially drawn shape is local until mouse release finalizes it.
- When looking up shapes containing a point, check in descending order to target top-most shape.
- For polylines, use the utility method `Segment.pointToSegmentDistance` to detect if the mouse is close enough to the polyline.
- After receiving server messages, always `repaint()` the editor to update the GUI.
- Keep the server authoritative to avoid inconsistent sketches.

---
