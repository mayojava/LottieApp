# Implementation
The App was built with Jetpack compose, using an architecture similar to MVVM architecture. 
AndroidX ViewModel is used for the ViewModel Implementation. Dagger Hilt is used for dependency 
injection and coroutines are used for offloading work off the main thread.

On launching the App, API calls are made in parallel to fetch the recent animations, featured animations,
popular animations, blog posts and featured animators. The ViewModel talks to a repository to 
fetch data.

## Repository
The repository uses the database as the source of truth. It returns the result of the db to its 
caller. It also refreshes the DB content with result from an API call.

The database is implemented using the Jetpack Room library. Since the return type of the DAO is a
`Flow`, any changes to the db content is always emitted and updates the view.
The repository calls are also main safe as they switches context to an IO dispatcher before calls
are made.

## Testing
JUnit is used to test the RemoteRepository and the Repository that wraps the remote and local sources.

## Running the App
Ensure you have the following in local.properties
featured.api.token={REPLACE WITH TOKEN FROM THE API URL}
popular.api.token={REPLACE WITH TOKEN FROM THE API URL}
recent.api.token={REPLACE WITH TOKEN FROM THE API URL}
animators.api.token={REPLACE WITH TOKEN FROM THE API URL}
blogs.api.token={REPLACE WITH TOKEN FROM THE API URL}
base.url=https://firebasestorage.googleapis.com/v0/b/lottiefiles-test.appspot.com/o/