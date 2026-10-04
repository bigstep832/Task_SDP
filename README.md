# Assignment 3 — Bridge Pattern

**Student:** Saken Zhadiger  
**Group:** SE-2538  
**Topic:** A — Drawing  
**Repository:** `PASTE_YOUR_GITHUB_URL_HERE`  
**Base commit:** `PASTE_BASE_COMMIT_HASH_HERE`

## Project Description

This project demonstrates the Bridge design pattern using shapes and renderers.

The abstraction hierarchy contains `Shape`, `Circle`, and `Square`.  
The implementation hierarchy contains `Renderer`, `VectorRenderer`, `RasterRenderer`, and `AsciiRenderer`.

`Circle` uses radius `2`, and `Square` uses side `3`.

The Bridge pattern allows a shape and a renderer to vary independently.

## Role Map

| Role | Class | Source file |
|---|---|---|
| Abstraction | `Shape` | `src/Shape.java` |
| Refined Abstraction A1 | `Circle` | `src/Circle.java` |
| Refined Abstraction A2 | `Square` | `src/Square.java` |
| Implementor | `Renderer` | `src/Renderer.java` |
| Implementation I1 | `VectorRenderer` | `src/VectorRenderer.java` |
| Implementation I2 | `RasterRenderer` | `src/RasterRenderer.java` |
| Implementation I3 | `AsciiRenderer` | `src/AsciiRenderer.java` |
| Client / Demo | `Main` | `src/Main.java` |

## Important Code Locations

- **Bridge field:** `Shape` stores a reference of type `Renderer`.
- **execute():** implemented by `Circle` and `Square`.
- **setImplementation(...):** declared in `Shape` and used to change the renderer at runtime.
- **T5 runtime switch:** implemented in `Main.java`.

## Build and Run

Compile the project:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
```

Run all required checks:

```bash
java -cp out Main --demo
```

## Expected Demonstration Checks

### T1
`Circle + VectorRenderer`

Expected result:

```text
VECTOR circle radius=2
```

### T2
`Circle + RasterRenderer`

Expected result:

```text
RASTER [circle radius=2]
```

### T3
`Square + VectorRenderer`

Expected result:

```text
VECTOR square side=3
```

### T4
`Square + RasterRenderer`

Expected result:

```text
RASTER [square side=3]
```

### T5
The same `Circle` object first uses `VectorRenderer`, then switches to `RasterRenderer`.

The check verifies:

- the object reference is the same using `==`;
- the ID does not change;
- the radius does not change;
- the output changes after switching the renderer.

Expected state:

```text
sameObject=true
stateUnchanged=true
```

### T6
`Circle + AsciiRenderer`

Expected result:

```text
ASCII <circle radius=2>
```

### T7
`Square + AsciiRenderer`

Expected result:

```text
ASCII <square side=3>
```

Expected final summary:

```text
SUMMARY: 7/7 PASS
```
```

## Project Files

```text
    Main.java
    Shape.java
    Circle.java
    Square.java
    Renderer.java
    VectorRenderer.java
    RasterRenderer.java
    AsciiRenderer.java
