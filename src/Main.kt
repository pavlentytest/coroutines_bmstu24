import kotlinx.coroutines.*

suspend fun main2() {
    for(i in 0..5) {
        delay(500)
        println(i)
    }
    println("Test message")
}
suspend fun main3() {
    coroutineScope {
        launch {
            for(i in 0..5) {
                delay(500)
                println(i)
            }
        }
        println("Test message")
    }
}
suspend fun main4() {
    coroutineScope {
        val job: Job = launch { heavyWork() }
        println("Started")
        job.join() // ждет завершения работы корутины
        println("End!")
    }
}
suspend fun main5() {
    coroutineScope {
        val job: Job = launch(start=CoroutineStart.LAZY) {
            heavyWork()
        }
        println("Started")
        delay(2000)
        job.start()// запускаем корутину
        job.cancel()
        job.join() // ждем завершения отмены
        job.cancelAndJoin() // отмена и ждем завершения

    }
}

suspend fun main() {
    coroutineScope {
        val result: Deferred<String> = async{
            getMessage()
        }
    }
}

suspend fun getMessage() : String {
    delay(2000)
    return "Hello!!!"
}

suspend fun heavyWork() {
    for(i in 0..5) {
        delay(1500L)
        println(i)
    }
}