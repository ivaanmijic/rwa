@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  video_voting_competition startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and VIDEO_VOTING_COMPETITION_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\video_voting_competition-1.0-SNAPSHOT.jar;%APP_HOME%\lib\jakarta.servlet.jsp.jstl-api-3.0.0.jar;%APP_HOME%\lib\jakarta.servlet-api-6.0.0.jar;%APP_HOME%\lib\jakarta.websocket-api-2.0.0.jar;%APP_HOME%\lib\google-api-services-youtube-v3-rev20250422-2.0.0.jar;%APP_HOME%\lib\google-api-client-2.7.2.jar;%APP_HOME%\lib\google-oauth-client-jetty-1.32.1.jar;%APP_HOME%\lib\google-oauth-client-java6-1.32.1.jar;%APP_HOME%\lib\google-oauth-client-1.36.0.jar;%APP_HOME%\lib\google-auth-library-oauth2-http-1.30.0.jar;%APP_HOME%\lib\google-http-client-gson-1.45.2.jar;%APP_HOME%\lib\gson-2.11.0.jar;%APP_HOME%\lib\datafaker-2.0.2.jar;%APP_HOME%\lib\hibernate-validator-8.0.1.Final.jar;%APP_HOME%\lib\jakarta.validation-api-3.0.2.jar;%APP_HOME%\lib\google-http-client-apache-v2-1.47.1.jar;%APP_HOME%\lib\google-http-client-jackson2-1.47.1.jar;%APP_HOME%\lib\hibernate-core-6.4.0.Final.jar;%APP_HOME%\lib\jakarta.servlet.jsp-api-3.1.1.jar;%APP_HOME%\lib\jakarta.servlet.jsp.jstl-3.0.1.jar;%APP_HOME%\lib\mysql-connector-j-8.2.0.jar;%APP_HOME%\lib\logback-classic-1.4.11.jar;%APP_HOME%\lib\slf4j-api-2.0.9.jar;%APP_HOME%\lib\jbcrypt-0.4.jar;%APP_HOME%\lib\snakeyaml-2.2.jar;%APP_HOME%\lib\generex-1.0.2.jar;%APP_HOME%\lib\jboss-logging-3.5.0.Final.jar;%APP_HOME%\lib\classmate-1.5.1.jar;%APP_HOME%\lib\google-http-client-1.47.1.jar;%APP_HOME%\lib\httpclient-4.5.14.jar;%APP_HOME%\lib\httpcore-4.4.16.jar;%APP_HOME%\lib\jackson-core-2.18.2.jar;%APP_HOME%\lib\jakarta.persistence-api-3.1.0.jar;%APP_HOME%\lib\jakarta.transaction-api-2.0.1.jar;%APP_HOME%\lib\hibernate-commons-annotations-6.0.6.Final.jar;%APP_HOME%\lib\jandex-3.1.2.jar;%APP_HOME%\lib\byte-buddy-1.14.7.jar;%APP_HOME%\lib\jaxb-runtime-4.0.2.jar;%APP_HOME%\lib\jaxb-core-4.0.2.jar;%APP_HOME%\lib\jakarta.xml.bind-api-4.0.0.jar;%APP_HOME%\lib\jakarta.inject-api-2.0.1.jar;%APP_HOME%\lib\antlr4-runtime-4.13.0.jar;%APP_HOME%\lib\jakarta.el-api-5.0.0.jar;%APP_HOME%\lib\protobuf-java-3.21.9.jar;%APP_HOME%\lib\logback-core-1.4.11.jar;%APP_HOME%\lib\automaton-1.11-8.jar;%APP_HOME%\lib\commons-codec-1.17.1.jar;%APP_HOME%\lib\google-auth-library-credentials-1.30.0.jar;%APP_HOME%\lib\opencensus-contrib-http-util-0.31.1.jar;%APP_HOME%\lib\guava-33.3.1-jre.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\error_prone_annotations-2.36.0.jar;%APP_HOME%\lib\j2objc-annotations-3.0.0.jar;%APP_HOME%\lib\opencensus-api-0.31.1.jar;%APP_HOME%\lib\grpc-context-1.70.0.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\angus-activation-2.0.0.jar;%APP_HOME%\lib\jakarta.activation-api-2.1.1.jar;%APP_HOME%\lib\auto-value-annotations-1.11.0.jar;%APP_HOME%\lib\grpc-api-1.70.0.jar;%APP_HOME%\lib\txw2-4.0.2.jar;%APP_HOME%\lib\istack-commons-runtime-4.1.1.jar;%APP_HOME%\lib\failureaccess-1.0.2.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-qual-3.43.0.jar


@rem Execute video_voting_competition
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %VIDEO_VOTING_COMPETITION_OPTS%  -classpath "%CLASSPATH%" com.example.video_voting.cli.AdminDataGenerator %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable VIDEO_VOTING_COMPETITION_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%VIDEO_VOTING_COMPETITION_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
