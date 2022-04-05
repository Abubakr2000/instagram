package com.example.elbekinstagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elbekinstagram.MainActivity
import com.example.elbekinstagram.Post
import com.example.elbekinstagram.PostAdapter
import com.example.elbekinstagram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

open class HomeFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    val allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //This is where we set up our views and click listeners
        view.findViewById<RecyclerView>(R.id.postRecyclerView)

        postsRecyclerView = view.findViewById(R.id.postRecyclerView)

        // Step to populate RecyclerView
        // 1. Create layout for each row in list(tem_post.xml) - DONE
        // 2. Create data source for each row(This is the post class) - DONE
        // 3. Create adapter that will bridge data nad layout (PostAdapter) - DONE
        // 4. Set Adapter on RecyclerView
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
        // 5. Set layout manager on RecyclerView
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()
    }

    // Query for all posts in our server
    open fun queryPosts() {

        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        // Find all Post objects
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        // TODO only return the most recent 20 post
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if(e != null)
                {
                    // Something has went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if(posts != null) {
                        for(post in posts) {
                            Log.i(TAG,"Post:" + post.getDescription() + " , username: " +
                                    post.getUser()?.username)
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
    companion object {
        const val TAG = "HomeFragment"
    }
}