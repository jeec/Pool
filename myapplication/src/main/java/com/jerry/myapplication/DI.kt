package com.jerry.myapplication

import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier

//对于普通类，直接通过在构造方法添加@Inject来注入
class MyTest @Inject constructor() {
    fun print(): Unit {
        Log.i(">>>", "直接通过构造方法来注入，获取实例！")
    }
}

//对于构造参数中有自定义接口类型的类，处理方法如下
class MyTestInterface @Inject constructor(myInterface: MyInterface){
    fun print(myInterface: MyInterface): Unit {
        myInterface.print()
    }
}

interface MyInterface {
    fun print()
}

class MyInterfaceImpl @Inject constructor() : MyInterface {
    override fun print() {
        Log.i(">>>", "接口类型完成注入")
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class InterfaceModule {
    @Binds
    abstract fun bindMyInterface(
        myInterfaceImpl: MyInterfaceImpl
    ): MyInterface
}

//对于构造参数中有非自有类，不能通过构造函数注入的，处理方法如下
class MyTestString @Inject constructor(str: String) {
    private val myStr = str
    fun print(): Unit {
        Log.i(">>>", myStr)
    }
}

@Module
@InstallIn(ActivityComponent::class)
object ParamsICantDefineModule {
    @Provides
    fun bindMyInterface(): String{
        return "参数为String类型，所以要用Provides来注解"
    }
}

//同一类型的不同实现，以String为例
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StringType_A

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StringType_B

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @StringType_A
    @Provides
    fun provideStringTypeA(
    ): String {
        return "String类型实现方式A：StringType_A"
    }

    @StringType_B
    @Provides
    fun provideStringTypeB(
    ): String {
        return "String类型实现方式B：StringType_B"
    }
}

