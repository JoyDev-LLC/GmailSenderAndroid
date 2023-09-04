# How to work with library

## 1. Add JitPack repository to your build file

```kotlin
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```
## 2. Add the dependency in your build.gradle

```kotlin
dependencies {
	implementation 'com.github.JoyDev-LLC:GmailSenderAndroid:Tag'
}
```

## 3. Initialize sender 

```kotlin
GmailServerSeyUp.setUpServerCredentials("<INTERNAL_MAILBOX>","<INTERNAL_PASSWORD>")
```

Internal mailbox is mailbox from which will come messages.
Internal password is password for internal mail.

## 4. Send Message

### Config our message
```kotlin
val message = GmailSenderBuilder()
		.setTitle("our_title")
		.setText("our_text")
		.setEmailsTo(EXTERNAL_MAILBOXES).build()
```

EXTERNAL_MAILBOXES - list of mailboxes  which will get messages from internal mail

### Send our message

```kotlin
message.sendMessage()
```

Method sendMessage returns boolean, so we can handle status.
True means that all good
False means that someting wrong. You can see errors in Logcat.
