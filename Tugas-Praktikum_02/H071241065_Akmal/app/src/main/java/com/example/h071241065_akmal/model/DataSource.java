package com.example.h071241065_akmal.model;

import com.example.h071241065_akmal.R;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Post> homePosts = new ArrayList<>();
    public static ArrayList<Post> profilePosts = new ArrayList<>();
    public static ArrayList<Highlight> highlights = new ArrayList<>();

    public static void generateDummyData() {
        if (homePosts.isEmpty()) {

            // ==========================================
            // 1. DATA HOME FEED (Minimal 10 Item Berbeda)
            // ==========================================

            homePosts.add(new Post(
                    R.drawable.messi, "akmal_065",
                    R.drawable.bintik, "Halo dunia! Ini postingan pertamaku di InstaClone."));

            homePosts.add(new Post(
                    R.drawable.messi, "budi_sanjaya",
                    R.drawable.bintik, "Menikmati senja di Pantai Losari. Indah banget! 🌅"));

            homePosts.add(new Post(
                    R.drawable.messi, "siti.aminah99",
                    R.drawable.kopi, "Akhirnya selesai ngerjain Tugas Praktikum 3! Semangat pejuang koding 💻🔥"));

            homePosts.add(new Post(
                    R.drawable.messi, "joko_tingkir",
                    R.drawable.dog, "Jalan-jalan keliling kota cari inspirasi buat project akhir semester."));

            homePosts.add(new Post(
                    R.drawable.messi, "dina_marlina",
                    R.drawable.bintik, "Makan coto makassar dulu biar fokus ngodingnya. Ada yang mau join? 🍲"));

            homePosts.add(new Post(
                    R.drawable.messi, "rezky.aditya",
                    R.drawable.hamster, "Jadwal kuliah minggu ini padat merayap. Bismillah bisa kating! 📚"));

            homePosts.add(new Post(
                    R.drawable.messi, "nisa_imut",
                    R.drawable.kopi, "OOTD hari ini buat ngampus. Gimana menurut kalian? ✨"));

            homePosts.add(new Post(
                    R.drawable.messi, "andi_gaming",
                    R.drawable.bintik, "Mabar Free Fire yok ntar malem! Push rank sampai pagi 🎮"));

            homePosts.add(new Post(
                    R.drawable.messi, "kucing_oren",
                    R.drawable.kopi, "Meow meow meow. (Terjemahan: Minta makan bang) 🐈"));

            homePosts.add(new Post(
                    R.drawable.messi, "tech_info.id",
                    R.drawable.img8, "Tahukah kamu? RecyclerView lebih hemat memori dibandingkan ListView loh! 💡"));




            // ==========================================
            // 2. DATA PROFILE POST (Minimal 5 Item Berbeda)
            // ==========================================
            profilePosts.add(new Post(
                    R.drawable.messi, "akmal_065",
                    R.drawable.bintik, "Nugas Pemrograman Mobile. Layouting pakai XML ternyata seru juga."));

            profilePosts.add(new Post(
                    R.drawable.messi, "akmal_065",
                    R.drawable.kopi, "Kopi dulu biar gak error syntax-nya ☕"));

            profilePosts.add(new Post(
                    R.drawable.messi, "akmal_065",
                    R.drawable.jembatan, "Setting up environment buat belajar Big Data pakai Hadoop. Wish me luck!"));

            profilePosts.add(new Post(
                    R.drawable.messi, "akmal_065",
                    R.drawable.bintik, "Momen kumpul bareng teman-teman Sistem Informasi UNHAS. Solid terus! 🚀"));

            profilePosts.add(new Post(
                    R.drawable.messi, "akmal_065",
                    R.drawable.kopi, "Throwback liburan semester kemaren. Kapan bisa jalan-jalan lagi ya?"));


            // ==========================================
            // 3. DATA HIGHLIGHT STORY (Minimal 7 Item Berbeda)
            // ==========================================
            highlights.add(new Highlight(R.drawable.sunset, "Liburan"));
            highlights.add(new Highlight(R.drawable.kopi, "Ngampus"));
            highlights.add(new Highlight(R.drawable.bintik, "Food"));
            highlights.add(new Highlight(R.drawable.jembatan, "Project"));
            highlights.add(new Highlight(R.drawable.hamster, "Tugas"));
            highlights.add(new Highlight(R.drawable.dog, "Healing"));
            highlights.add(new Highlight(R.drawable.img8, "Random"));
        }
    }
}