# Kwartracker-Android	

Project Information
---

Setup
---

Dependencies / Libraries
---

* [CardView](https://developer.android.com/guide/topics/ui/layout/cardview)
* [CircularImageView](https://github.com/hdodenhof/CircleImageView)
* [DataStore](https://developer.android.com/jetpack/androidx/releases/datastore)
* [Espresso](https://developer.android.com/training/testing/espresso)
* [Glide](https://github.com/bumptech/glide)
* [Hilt](https://dagger.dev/hilt/)
* [Mockito](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
* [Realm](https://docs.mongodb.com/realm-legacy/docs/kotlin/latest/#models)
* [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview)
* [Retrofit](https://square.github.io/retrofit/)

Developer Cheat Sheet
---

####Navigate to fragments　

```javascript
findNavController().navigate(R.id.walletsFragment)
```

####Bottom Sheet Modal　

```javascript
val intent = Intent("message")
intent.putExtra("func", "filter")
intent.putExtra("fragment", "TransactionsFilterFragment")
LocalBroadcastManager.getInstance(view.context).sendBroadcast(intent)
```