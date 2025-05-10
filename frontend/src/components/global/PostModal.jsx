import React, { useEffect, useState } from "react";
import { X, Heart, Send } from "lucide-react";
import { fetchCommentsByPost, addComment } from "../../services/commentService";
import { likePost, unlikePost, fetchLikeCount, checkIsLiked } from "../../services/likeService";
import { useAuth } from "../../context/AuthContext";

const PostModal = ({ isOpen, onClose, post }) => {
  const { user } = useAuth();
  const [comments, setComments] = useState([]);
  const [commentText, setCommentText] = useState("");
  const [likes, setLikes] = useState(0);
  const [liked, setLiked] = useState(false);


 useEffect(() => {
  if (post?.id && user?.id) {
    fetchCommentsByPost(post.id).then(setComments);
    fetchLikeCount(post.id).then(setLikes);
    checkIsLiked(post.id, user.id).then(setLiked).catch(() => setLiked(false));
  }
}, [post, user]);



  const handleCommentSubmit = async () => {
    if (!commentText.trim()) return;
    const newComment = await addComment({
      postId: post.id,
      userId: user.id,
      content: commentText,
    });
    setComments((prev) => [...prev, newComment]);
    setCommentText("");
  };

  const handleLikeToggle = async () => {
  try {
    if (liked) {
      await unlikePost(post.id, user.id);
    } else {
      await likePost(post.id, user.id);
    }
    setLiked(!liked);
    const count = await fetchLikeCount(post.id);
    setLikes(count);
  } catch (err) {
    console.error("Error toggling like", err);
  }
};


  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 bg-black bg-opacity-80 flex justify-center items-center">
      <div className="bg-[#111] w-full max-w-5xl h-[90vh] rounded-lg overflow-hidden flex relative">
        <button onClick={onClose} className="absolute top-4 right-4 text-gray-400 hover:text-white">
          <X size={24} />
        </button>

        {/* Left: Image */}
        <div className="w-1/2 bg-black">
          <img src={post.src} alt={post.caption} className="h-full w-full object-cover" />
          <div className="absolute top-4 left-4 bg-black bg-opacity-60 px-4 py-2 rounded-lg">
            <p className="text-sm text-white font-semibold">@{post.username}</p>
            <p className="text-sm text-gray-300">{post.caption}</p>
          </div>
        </div>

        {/* Right: Comments */}
        <div className="w-1/2 bg-[#1a1a1a] p-6 overflow-y-auto">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-xl font-bold text-white">Comments</h2>
            <button onClick={handleLikeToggle}>
  <Heart className={`w-5 h-5 ${liked ? "text-red-500" : "text-gray-400"}`} />
  <span>{likes}</span>
</button>

          </div>

          <div className="space-y-4 mb-6">
            {comments.map((c) => (
              <div key={c.id} className="text-sm text-gray-300">
                <strong>@{c.userId}</strong>: {c.content}
              </div>
            ))}
          </div>

          <div className="flex items-center gap-2 mt-auto">
            <input
              type="text"
              value={commentText}
              onChange={(e) => setCommentText(e.target.value)}
              placeholder="Add a comment..."
              className="w-full px-4 py-2 bg-black text-white rounded-lg border border-gray-600 focus:outline-none"
            />
            <button onClick={handleCommentSubmit} className="text-BabyBlue hover:text-white">
              <Send size={20} />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PostModal;
