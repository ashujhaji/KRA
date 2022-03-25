# KRA UI

A reference android project with the combination of MVVM architecture and LiveData. The objective is to create a minimal UI to get user input. Input will be PAN and DOB and both should be validated at client end.


### Life cycle awareness
For an app, it is important to be life-cycle aware to prevent crashes while rotating screen or while going app in background. Suppose a case on slow internet, you sent a network call but due to slow internet it took some time to send response. During this, you exit the app and it is in background now. After sometime app got the network response and tried to render it on UI. But our cannot perform this because it is not visible currently and it got crashed.
For this purpose, there is an life-cycle aware architecture component of android, Live Data. Live Data has an observer which observe for changes and render it on UI if and only if it is okay to render.


### Highlighted points
* Architecture followed : MVVM with Live Data
* Data binding has been used
* Regex to validate inputs
* Slector drawables to manage input fields
* There was no need to use Co-routine to achieve the given task


### Tech

This project consist of number of open source libraries to work properly:

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - For life-cycle awareness

And of course this itself is open source with a public repository
 on GitHub.

License
----
MIT License

        Copyright (c) 2020 Ashutosh Jha

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
