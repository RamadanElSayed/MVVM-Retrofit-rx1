package com.kotlin.mvvm_retrofit_rx
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    lateinit var service:JsonPlaceholderApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit=RetrofitClient.instance
        compositeDisposable=CompositeDisposable()
        service=retrofit.create(JsonPlaceholderApi::class.java)
        compositeDisposable.add(service.posts.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
        subscribe(this::postListsFun,this::handleError))
    }

    private fun postListsFun(list:List<Post>)
    {
        val reycler:RecyclerView=findViewById(R.id.post_list)
       // val adapter:PostListAdapter=PostListAdapter(list)
        val adapterList = PostListAdapter(list)
        reycler.apply {
            hasFixedSize()
            layoutManager=LinearLayoutManager(applicationContext)
            adapter=adapterList
        }
    }

    private fun handleError(error: Throwable) {

        Logger.getLogger(MainActivity::class.java.name).warning("   Error: $error.localizedMessage")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
