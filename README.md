# Project Six - Notes App

This Android app is designed to help you manage your notes efficiently. It provides a user-friendly interface for creating, editing, and deleting notes. The app utilizes Android's Room Library for local database storage and offers a seamless user experience for managing your notes.

## Functionality

The app includes the following key functionalities:

- **Notes List**: The home screen of the app displays a list of notes, with each note represented by a title. The list is presented using a RecyclerView and allows users to easily view their notes.

- **Note Details**: When a user taps on a note title in the list, it navigates to a detailed view in a fragment called Note Fragment. This view displays the title and description of the note.

- **Add Note**: Users can add a new note by tapping the "Add Note" button on the main screen. This action opens a new screen for creating a note in the Note Fragment.

- **Edit Note**: Users can also edit an existing note by tapping on the note's title in the list. This allows them to modify the note's title and description.

- **Delete Note**: Each note in the list includes a delete button. When users tap this button, a confirmation dialog appears to confirm if they want to delete the note.

- **Local Database**: All notes are stored in a local database using the Room Library. This ensures that users' notes are persistent and can be accessed even after closing the app.

- **DiffUtil for UI Updates**: The RecyclerView in the notes list uses DiffUtil for efficient updates when notes are added, edited, or deleted. This ensures a smooth user interface.


## Video Walkthrough
https://www.loom.com/share/7c27ac4cdbc0435d9cc9b6cebdf396ef

Loom | Free Screen & Video Recording Software
Use Loom to record quick videos of your screen and cam. Explain anything clearly and easily – and skip the meeting. An essential tool for hybrid workplaces.
www.loom.com


## Notes

The app's logic is fully implemented and functional, but there might be a minor issue causing it not to compile. Also Androidstudio wouldn't let me share my project

## License

Copyright 2023 by George Sackie

Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. You may obtain a copy of the License at [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0).

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
