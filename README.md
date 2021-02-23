# EXOMIND
## Technical Exercice
Retrive user list from: https://jsonplaceholder.typicode.com/users
Retrieve user Album from: https://jsonplaceholder.typicode.com/users/1/albums?userId={userID} ,
Retrieve album pictures from: https://jsonplaceholder.typicode.com/users/1/photos?albumId={albumID}.

The app has following packages:
- injection:  Manipulating components.
- Local: Local database and DAO .
- Model: Pojo .
- Network: Api service .
- UI: View classes along with their corresponding ViewModel.
- Utils: Utility classes.

## Tech

Library reference resources::

- [Room Persistence Library] - https://developer.android.com/topic/libraries/architecture/room.html
- [RxJava2] -  https://github.com/amitshekhariitbhu/RxJava2-Android-Samples
- [Dagger2] - : https://github.com/MindorksOpenSource/android-dagger2-example
- [Retrofit] - : https://square.github.io/retrofit/
- [Data Binding Library  ] - : https://developer.android.com/topic/libraries/data-binding


## Architecture

The MVVM pattern is a pattern derived from the MVP pattern defined by Martin Fowler in the 1990s. The MVVM (Model-View-ViewModel) pattern helps to completely separate the business and presentation logic from the UI, and the business logic and UI can be clearly separated for easier testing and easier maintenance. Let’s take a look at View, ViewModel and Model.

 
