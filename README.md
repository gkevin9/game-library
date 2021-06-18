How to use?
1. Clone project
2. Change API Key for RAWG in core/src/main/java/com/gkevin/gamecatalogue/core/data/source/remote/network/ApiService.kt
3. Change or delete certificate pinner in core/src/main/java/com/gkevin/gamecatalogue/core/di/CoreModule.kt at line 33
4. Run the project

Features include:
- Clean Architecture
  + view model
  + live data
  + MVVM
- Database
  + room
  + SQLCipher
- API
  + retrofit
  + GSON
  + certificate pinning
- Reactive Programming
  + RxJava
- Dependency Injection
  + koin
- Modularization
  + modularization
  + dynamic feature
