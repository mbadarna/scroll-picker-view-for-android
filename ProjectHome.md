# Introduction #

ScrollPickerView, DatePickerView, TimePickerView for Android.

iOS風のScrollPickerViewを提供するライブラリプロジェクトです。
表示イメージは以下のとおりです。


  * ScrollPickerView
> > ![http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133950.png](http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133950.png)

  * DateScrollPickerView
> > ![http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133949.png](http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133949.png)


  * TimeScrollPickerView
> > ![http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133948.png](http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133948.png)


  * customize ScrollPickerView
> > ![http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133953.png](http://cdn-ak.f.st-hatena.com/images/fotolife/t/ttshrk/20110920/20110920133953.png)


# Details #

  * 使い方について。

ソースコードをダウンロード後、ライブラリプロジェクトとして追加します。
(res配下のリソースを利用しているため、jarファイルでは提供していません。)

Androidライブラリプロジェクトの詳細については以下を参考にしてください。<br>
Library Projects<br>
<a href='http://developer.android.com/intl/ja/guide/developing/projects/index.html#considerations'>http://developer.android.com/intl/ja/guide/developing/projects/index.html#considerations</a>


<ul><li>サンプルコード<br>
<blockquote>より具体的なコードはDownloads/DateTimePickerSample.zipを参照してください。</blockquote></li></ul>

<ul><li>ScrollPickerView<br>
<pre><code>ScrollPickerView scrollPickerView = new ScrollPickerView(context);<br>
scrollPickerView.addSlot(getResources().getStringArray(R.array.custom_list), 1, ScrollPickerView.ScrollType.Ranged);<br>
scrollPickerView.setSlotIndex(0, 13);<br>
ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, 200);<br>
<br>
setContentView(scrollPickerView, params);<br>
</code></pre></li></ul>

<ul><li>DateScrollPickerView<br>
<pre><code>DateScrollPickerView datePickerView = new DateScrollPickerView(context);<br>
datePickerView.setCurrentDate(false);<br>
    	<br>
ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(300, 300);<br>
    	<br>
setContentView(datePickerView, params);<br>
</code></pre></li></ul>

<ul><li>TimeScrollPickerView<br>
<pre><code>TimeScrollPickerView timePickerView = new TimeScrollPickerView(context);<br>
timePickerView.setCurrentTime(false);<br>
    	<br>
ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, 300);<br>
    	<br>
setContentView(timePickerView, params);<br>
</code></pre></li></ul>


<h1>今後の対応予定</h1>

<ul><li>change design<br>
<blockquote>text-shadow<br>
change alpha layer for image<br>
fix text label width</blockquote></li></ul>

<ul><li>support to change labels<br>
<blockquote>suport to change labels on SlotView</blockquote></li></ul>

<ul><li>support to change show count<br>
<blockquote>support to change show label count on SlotView