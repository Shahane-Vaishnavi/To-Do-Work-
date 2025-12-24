# ToDoWork (Android)

Simple to‑do list app written in Java. It shows a splash screen, then opens a single to‑do list screen backed by a local SQLite database. Tasks can be created, edited, marked complete, or deleted.

## Features
- 3s splash screen (`activity_splash_screen`) before entering the app.
- Main list (`activity_main`) backed by `RecyclerView`/`ToDoAdapter`.
- Add/edit task bottom sheet (`add_new_task`), stores items in SQLite (`DataBaseHelper`).
- Checkboxes update completion status; long‑press menu handled in adapter methods.
- Local persistence only; no network requirements.

## Project structure
- `app/src/main/java/com/example/todowork`
  - `SplashScreen`: launcher activity, forwards to `MainActivity`.
  - `MainActivity`: sets up list, FAB opens `AddNewTask`.
  - `AddNewTask`: bottom sheet dialog for create/update.
  - `Adapter/ToDoAdapter`: binds tasks to `task_layout` cards.
  - `Model/ToDoModel`: simple POJO.
  - `Utils/DataBaseHelper`: SQLiteOpenHelper for CRUD.
- Layouts (`app/src/main/res/layout`)
  - `activity_splash_screen.xml`, `activity_main.xml`, `add_new_task.xml`, `task_layout.xml`.

## Build & run
1) Open the project in Android Studio (AGP/Gradle wrappers are included).  
2) Use `Build > Make Project` to download dependencies.  
3) Run on a device/emulator with **minSdk 24**, **target/compileSdk 36**.

## Why some layouts “don’t run”
Only two activities are registered in `AndroidManifest.xml`: `SplashScreen` (launcher) and `MainActivity`. The other XML files are used by these screens (e.g., `add_new_task` is a bottom sheet, `task_layout` is a row item). To show additional pages you created, you must either:
- Create a new Activity/Fragment and set its `setContentView(...)` to your layout, or
- Navigate to it from existing screens (intent/fragment transaction) and register the Activity in the manifest.

## Notes / troubleshooting
- If tasks don’t appear after adding, ensure `DataBaseHelper` is working and `onDialogClose` is triggered (the bottom sheet dismisses to refresh the list).
- Database is stored locally; uninstalling the app clears tasks.
- To change splash duration, edit the `3000` ms delay in `SplashScreen`.

