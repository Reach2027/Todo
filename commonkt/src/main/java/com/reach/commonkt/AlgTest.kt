package com.reach.commonkt

fun main() {
    val test: Array<Int> = arrayOf(1, 5, 3, 2, 3, 4, 2)
    test.insertionSort()
    print(test.contentToString())
}

fun <T : Comparable<T>> Array<T>.selectionSort() {
    for (i in 0 until this.lastIndex) {
        for (j in i + 1..this.lastIndex) {
            if (this[i] > this[j]) {
                // kotlin swap
                this[i] = this[j].also { this[j] = this[i] }
            }
        }
    }
}

fun <T : Comparable<T>> Array<T>.bubbleSort() {
    for (i in this.lastIndex downTo 1) {
        var finished = true
        for (j in 1..i) {
            if (this[j - 1] > this[j]) {
                finished = false
                // kotlin swap
                this[j - 1] = this[j].also { this[j] = this[j - 1] }
            }
        }
        if (finished) break
    }
}

fun <T : Comparable<T>> Array<T>.insertionSort() {
    for (i in 1..this.lastIndex) {
        for (j in i downTo 1) {
            if (this[j - 1] > this[j]) {
                // kotlin swap
                this[j - 1] = this[j].also { this[j] = this[j - 1] }
            } else {
                break
            }
        }
    }
}