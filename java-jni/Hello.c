#include "Hello.h"
#include <stdio.h>

JNIEXPORT jint JNICALL Java_Hello_say(JNIEnv* env, jobject thiz, jint i) {
    printf("Hello,I am JNI\n");
    return i + 100;
}
