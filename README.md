# CstvApp
Repository for Fuze code challenge

Architecture - Multi-Module with Clean Architecture and MVVM.

<br />
<img width="278" height="471" alt="image" src="https://github.com/user-attachments/assets/a288ae51-da7f-4f7d-8715-2db67884a5f0" />
<br />
<br />
The app module basically contains Application and Activity and the structure to receive more feature modules.
<br />
<br />
You can find tests of the two ViewModels(MatchesListViewModelTest and MatchDetailsViewModelTest) in feature/matches/test and test of MatchesPagingSource in core/repository/test(MatchesPagingSourceTest)
<br />
<br />
I left the pull requests created but merged all the code into main to make it easier to view.
<br />
<br />
Libraries:
<br />
UI - Compose
<br />
Pagination - Paging 3
<br />
Compose navigation - Type-safe navigation
<br />
Dependency injection - Hilt
<br />
Network - Retrofit and Okhttp
<br />
Image - loading and caching - Coil
<br />
Tests - Junit, Mockito e Mockk
<br />
<br />
You can run the app by compiling the main branch or by installing the not signed following apk:
https://drive.google.com/file/d/15A5GSQWuNXkfVXfYbY7oufCPhARvPe4W/view?usp=sharing
