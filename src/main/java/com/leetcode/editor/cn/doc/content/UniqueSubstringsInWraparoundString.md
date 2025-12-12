<p>We define the string <code>base</code> to be the infinite wraparound string of <code>"abcdefghijklmnopqrstuvwxyz"</code>, so <code>base</code> will look like this:</p>

<ul> 
 <li><code>"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...."</code>.</li> 
</ul>

<p>Given a string <code>s</code>, return <em>the number of <strong>unique non-empty substrings</strong> of </em><code>s</code><em> are present in </em><code>base</code>.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "a"
<strong>Output:</strong> 1
<strong>Explanation:</strong> Only the substring "a" of s is in base.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = "cac"
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two substrings ("a", "c") of s in base.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = "zab"
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are six substrings ("z", "a", "b", "za", "ab", and "zab") of s in base.
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>s</code> consists of lowercase English letters.</li> 
</ul>

<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 452</li><li>👎 0</li></div>