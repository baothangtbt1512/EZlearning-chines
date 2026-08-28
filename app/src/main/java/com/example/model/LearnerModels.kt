package com.example.model

enum class SkillType(val displayName: String) {
  VOCABULARY("Từ vựng"),
  LISTENING("Luyện nghe"),
  READING("Luyện đọc"),
  SPEAKING("Luyện nói"),
  PRONUNCIATION("Phát âm"),
  WRITING("Tập viết"),
  GRAMMAR("Ngữ pháp"),
  CONVERSATION("Hội thoại")
}

data class SkillProgress(
  val type: SkillType,
  val percentage: Int = 0
)

enum class NodeIconType {
  HOME_BASE,
  HELLO_GATE,
  TONE_GAME,
  PANDA_FRIEND,
  MY_NAME,
  MY_COUNTRY,
  NUMBERS,
  FIRST_CONVERSATION,
  FIRST_CONVERSATION_BOSS,
  GENERAL_LESSON,
  BOSS_CHALLENGE
}

data class JourneyNode(
  val id: String,
  val name: String,
  val iconType: NodeIconType,
  val isCurrent: Boolean = false,
  val isLocked: Boolean = true,
  val isCompleted: Boolean = false,
  val pinyin: String = "",
  val chinese: String = "",
  val englishSummary: String = "",
  val worldNumber: Int = 1
)

data class WorldData(
  val id: String,
  val number: Int,
  val chinese: String,
  val english: String,
  val description: String,
  val iconEmoji: String,
  val isCompleted: Boolean = false,
  val isCurrent: Boolean = false,
  val isLocked: Boolean = true,
  val nodes: List<JourneyNode> = emptyList()
)

data class AchievementItem(
  val id: String,
  val title: String,
  val iconLabel: String,
  val isUnlocked: Boolean = false
)

data class LearnerState(
  val name: String = "",
  val country: String = "Việt Nam",
  val level: Int = 1,
  val xp: Int = 0,
  val dailyGoalTarget: Int = 10,
  val dailyGoalCurrent: Int = 0,
  val streakDays: Int = 0,
  val currentWorldId: String = "world_1",
  val currentNodeId: String = "home_base",
  val skills: Map<SkillType, Int> = mapOf(
    SkillType.VOCABULARY to 0,
    SkillType.LISTENING to 0,
    SkillType.SPEAKING to 0,
    SkillType.PRONUNCIATION to 0,
    SkillType.GRAMMAR to 0
  ),
  val soundEffectsEnabled: Boolean = true,
  val remindersEnabled: Boolean = true,
  val worlds: List<WorldData> = defaultWorldsData,
  val journeyNodes: List<JourneyNode> = defaultWorldsData.first().nodes,
  val achievements: List<AchievementItem> = initialAchievements,
  val isOnboardingCompleted: Boolean = false,
  val avatar: String = "dudu"
)

val initialWorld1Nodes = listOf(
  JourneyNode(
    id = "w1_n1",
    name = "Khởi Đầu",
    iconType = NodeIconType.HOME_BASE,
    isCurrent = true,
    isLocked = false,
    isCompleted = false,
    pinyin = "Nǐ hǎo",
    chinese = "你好",
    englishSummary = "Chào mừng bạn đến với hành trình! Hãy bắt đầu bằng lời chào.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n2",
    name = "Cổng Chào",
    iconType = NodeIconType.HELLO_GATE,
    isCurrent = false,
    isLocked = true,
    pinyin = "Nǐ hǎo ma",
    chinese = "你好吗",
    englishSummary = "Vượt qua cổng Vạn Lý Trường Thành.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n3",
    name = "Thanh Điệu",
    iconType = NodeIconType.TONE_GAME,
    isCurrent = false,
    isLocked = true,
    pinyin = "Shēngdiào",
    chinese = "声调",
    englishSummary = "Làm chủ 4 thanh điệu trong tiếng Trung.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n4",
    name = "Bạn Gấu Trúc",
    iconType = NodeIconType.PANDA_FRIEND,
    isCurrent = false,
    isLocked = true,
    pinyin = "Xióngmāo",
    chinese = "熊猫",
    englishSummary = "Gặp gỡ Bảo Bảo - chú gấu trúc đồng hành.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n5",
    name = "Tên Của Tôi",
    iconType = NodeIconType.MY_NAME,
    isCurrent = false,
    isLocked = true,
    pinyin = "Wǒ jiào...",
    chinese = "我叫...",
    englishSummary = "Giới thiệu họ tên bằng tiếng Trung.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n6",
    name = "Quốc Gia",
    iconType = NodeIconType.MY_COUNTRY,
    isCurrent = false,
    isLocked = true,
    pinyin = "Guójiā",
    chinese = "国家",
    englishSummary = "Nói về quê hương và quốc tịch.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n7",
    name = "Chữ Số",
    iconType = NodeIconType.NUMBERS,
    isCurrent = false,
    isLocked = true,
    pinyin = "Yī, èr, sān...",
    chinese = "一二三",
    englishSummary = "Đếm từ 1 đến 10 bằng tiếng Trung.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n8",
    name = "Hội Thoại Đầu Tiên",
    iconType = NodeIconType.FIRST_CONVERSATION,
    isCurrent = false,
    isLocked = true,
    pinyin = "Duìhuà",
    chinese = "对话",
    englishSummary = "Cuộc trò chuyện thực tế đầu tiên của bạn.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n9",
    name = "Thử Thách Bậc Thầy",
    iconType = NodeIconType.FIRST_CONVERSATION_BOSS,
    isCurrent = false,
    isLocked = true,
    pinyin = "Dàshī",
    chinese = "大师挑战",
    englishSummary = "Bài kiểm tra tổng hợp cùng Bậc Thầy Trường Thành.",
    worldNumber = 1
  )
)

val initialWorld2Nodes = listOf(
  JourneyNode("w2_n1", "Sinh Hoạt Hàng Ngày", NodeIconType.GENERAL_LESSON, false, true, false, "Qǐchuáng", "起床与早晨", "Học thói quen từ sáng đến tối.", 2),
  JourneyNode("w2_n2", "Xem Giờ", NodeIconType.GENERAL_LESSON, false, true, false, "Xiànzài jǐ diǎn", "几点", "Nói giờ, phút và thời gian chuẩn xác.", 2),
  JourneyNode("w2_n3", "Thứ & Ngày Tháng", NodeIconType.GENERAL_LESSON, false, true, false, "Xīngqī yǔ rìqī", "星期与日期", "Nói các ngày trong tuần và tháng.", 2),
  JourneyNode("w2_n4", "Sở Thích", NodeIconType.GENERAL_LESSON, false, true, false, "Àihào", "我的爱好", "Bày tỏ thể thao, âm nhạc và giải trí.", 2),
  JourneyNode("w2_n5", "Trùm Đời Sống", NodeIconType.BOSS_CHALLENGE, false, true, false, "Shēnghuó tiǎozhàn", "生活挑战", "Hoàn thành hội thoại lịch trình một ngày.", 2)
)

val initialWorld3Nodes = listOf(
  JourneyNode("w3_n1", "Thành Viên Gia Đình", NodeIconType.GENERAL_LESSON, false, true, false, "Jiārén", "家人", "Giới thiệu cha mẹ, anh chị em và người thân.", 3),
  JourneyNode("w3_n2", "Nhà Cửa & Phòng", NodeIconType.GENERAL_LESSON, false, true, false, "Fángjiān", "我的房间", "Khám phá phòng khách, bếp và phòng ngủ.", 3),
  JourneyNode("w3_n3", "Thú Cưng Đáng Yêu", NodeIconType.GENERAL_LESSON, false, true, false, "Chǒngwù", "宠物", "Từ vựng về chó, mèo và chim muông.", 3),
  JourneyNode("w3_n4", "Đồ Dùng Gia Đình", NodeIconType.GENERAL_LESSON, false, true, false, "Jiājù", "家具物品", "Các vật dụng và nội thất trong nhà.", 3),
  JourneyNode("w3_n5", "Trùm Câu Chuyện Gia Đình", NodeIconType.BOSS_CHALLENGE, false, true, false, "Jiātíng gùshì", "家庭故事", "Kể câu chuyện gia đình bằng tiếng Trung.", 3)
)

val initialWorld4Nodes = listOf(
  JourneyNode("w4_n1", "Lớp Học & Môn Học", NodeIconType.GENERAL_LESSON, false, true, false, "Jiàoshì", "教室与学科", "Nói về trường lớp, môn học và thầy cô.", 4),
  JourneyNode("w4_n2", "Kết Bạn", NodeIconType.GENERAL_LESSON, false, true, false, "Jiāo péngyǒu", "交朋友", "Gặp gỡ bạn bè và hẹn hò.", 4),
  JourneyNode("w4_n3", "Ý Kiến & Cảm Xúc", NodeIconType.GENERAL_LESSON, false, true, false, "Xiǎngfǎ", "表达想法", "Chia sẻ điều bạn thích và không thích.", 4),
  JourneyNode("w4_n4", "Trùm Trường Học", NodeIconType.BOSS_CHALLENGE, false, true, false, "Xiàoyuán tiǎozhàn", "校园挑战", "Dẫn dắt buổi thảo luận câu lạc bộ học sinh.", 4)
)

val initialWorld5Nodes = listOf(
  JourneyNode("w5_n1", "Rau Củ & Trái Cây", NodeIconType.GENERAL_LESSON, false, true, false, "Shuǐguǒ", "水果蔬菜", "Khám phá nông sản chợ Bắc Kinh.", 5),
  JourneyNode("w5_n2", "Mặc Cả & Giá Cả", NodeIconType.GENERAL_LESSON, false, true, false, "Duōshǎo qián", "多少钱", "Hỏi giá và trả giá khi mua sắm.", 5),
  JourneyNode("w5_n3", "Thanh Toán Di Động", NodeIconType.GENERAL_LESSON, false, true, false, "Zhīfù", "扫码支付", "Sử dụng WeChat và Alipay mượt mà.", 5),
  JourneyNode("w5_n4", "Trùm Chợ Búa", NodeIconType.BOSS_CHALLENGE, false, true, false, "Shìchǎng táojīn", "市场淘金", "Hoàn thành nhiệm vụ mua sắm tại chợ.", 5)
)

val initialWorld6Nodes = listOf(
  JourneyNode("w6_n1", "Gọi Món Ăn", NodeIconType.GENERAL_LESSON, false, true, false, "Diǎncài", "点菜", "Gọi mì, há cảo và vịt quay Bắc Kinh.", 6),
  JourneyNode("w6_n2", "Hương Vị", NodeIconType.GENERAL_LESSON, false, true, false, "Suān tián kǔ là", "酸甜苦辣", "Hiểu vị cay, ngọt, mặn và chua.", 6),
  JourneyNode("w6_n3", "Trà & Tráng Miệng", NodeIconType.GENERAL_LESSON, false, true, false, "Chá yǔ tiándiǎn", "茶与甜点", "Văn hóa trà đạo và các món tráng miệng.", 6),
  JourneyNode("w6_n4", "Trùm Đại Tiệc", NodeIconType.BOSS_CHALLENGE, false, true, false, "Yànxí tiǎozhàn", "宴席挑战", "Chủ trì bữa tiệc ăn mừng thịnh soạn.", 6)
)

val initialWorld7Nodes = listOf(
  JourneyNode("w7_n1", "Địa Danh Thành Phố", NodeIconType.GENERAL_LESSON, false, true, false, "Dìbiāo", "城市地标", "Tìm ngân hàng, công viên, bảo tàng và cửa hàng.", 7),
  JourneyNode("w7_n2", "Hỏi Đường", NodeIconType.GENERAL_LESSON, false, true, false, "Wènlù", "问路", "Rẽ trái, rẽ phải và đi thẳng.", 7),
  JourneyNode("w7_n3", "Tàu Điện & Taxi", NodeIconType.GENERAL_LESSON, false, true, false, "Dìtiě yǔ chūzūchē", "地铁与出租车", "Di chuyển bằng phương tiện công cộng.", 7),
  JourneyNode("w7_n4", "Trùm Hướng Dẫn Viên", NodeIconType.BOSS_CHALLENGE, false, true, false, "Dǎoyóu tiǎozhàn", "导游挑战", "Hướng dẫn du khách khám phá thành phố.", 7)
)

val initialWorld8Nodes = listOf(
  JourneyNode("w8_n1", "Sân Bay & Tàu Cao Tốc", NodeIconType.GENERAL_LESSON, false, true, false, "Jīchǎng", "机场与高铁", "Lên tàu cao tốc và các chuyến bay.", 8),
  JourneyNode("w8_n2", "Nhận Phòng Khách Sạn", NodeIconType.GENERAL_LESSON, false, true, false, "Jiǔdiàn", "酒店入住", "Đặt phòng và yêu cầu dịch vụ tiện ích.", 8),
  JourneyNode("w8_n3", "Danh Lam Thắng Cảnh", NodeIconType.GENERAL_LESSON, false, true, false, "Guānguāng", "名胜古迹", "Tham quan công viên quốc gia và di sản.", 8),
  JourneyNode("w8_n4", "Trùm Thám Hiểm", NodeIconType.BOSS_CHALLENGE, false, true, false, "Lǚxíng dàshī", "旅行大师", "Lập kế hoạch du lịch xuyên quốc gia.", 8)
)

val initialWorld9Nodes = listOf(
  JourneyNode("w9_n1", "Tử Cấm Thành", NodeIconType.GENERAL_LESSON, false, true, false, "Gùgōng", "故宫探秘", "Khám phá hoàng cung và các triều đại.", 9),
  JourneyNode("w9_n2", "Đỉnh Trường Thành", NodeIconType.GENERAL_LESSON, false, true, false, "Chángchéng", "登长城", "Chạm tới ngọn tháp canh cao nhất.", 9),
  JourneyNode("w9_n3", "Ngõ Hẻm Hồ Đồng", NodeIconType.GENERAL_LESSON, false, true, false, "Hútòng", "胡同寻味", "Khám phá nét đẹp văn hóa ngõ cổ Bắc Kinh.", 9),
  JourneyNode("w9_n4", "Thử Thách Thần Long", NodeIconType.BOSS_CHALLENGE, false, true, false, "Lóngshén kǎohé", "终极龙神考核", "Bài thi khảo hạch tiếng Trung tối thượng.", 9)
)

val defaultWorldsData = listOf(
  WorldData(
    id = "world_1",
    number = 1,
    chinese = "你好！",
    english = "Lần Đầu Gặp Gỡ",
    description = "Pinyin, chào hỏi, thanh điệu, chữ số & giới thiệu bản thân",
    iconEmoji = "👋",
    isCompleted = false,
    isCurrent = true,
    isLocked = false,
    nodes = initialWorld1Nodes
  ),
  WorldData(
    id = "world_2",
    number = 2,
    chinese = "我的生活",
    english = "Cuộc Sống Của Tôi",
    description = "Thói quen hàng ngày, thời gian, ngày tháng & sở thích",
    iconEmoji = "☀️",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld2Nodes
  ),
  WorldData(
    id = "world_3",
    number = 3,
    chinese = "我的家",
    english = "Ngôi Nhà Của Tôi",
    description = "Gia đình, các phòng, thú cưng & vật dụng trong nhà",
    iconEmoji = "🏡",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld3Nodes
  ),
  WorldData(
    id = "world_4",
    number = 4,
    chinese = "学校与朋友",
    english = "Trường Học & Bạn Bè",
    description = "Đời sống học đường, kết bạn & bày tỏ quan điểm",
    iconEmoji = "🎒",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld4Nodes
  ),
  WorldData(
    id = "world_5",
    number = 5,
    chinese = "北京市场",
    english = "Chợ Bắc Kinh",
    description = "Trái cây tươi, mặc cả, tiền tệ & thanh toán điện tử",
    iconEmoji = "🏮",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld5Nodes
  ),
  WorldData(
    id = "world_6",
    number = 6,
    chinese = "餐厅",
    english = "Nhà Hàng",
    description = "Gọi món, các loại hương vị ẩm thực & văn hóa trà đạo",
    iconEmoji = "🥢",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld6Nodes
  ),
  WorldData(
    id = "world_7",
    number = 7,
    chinese = "我的城市",
    english = "Thành Phố Của Tôi",
    description = "Địa danh thành phố, hỏi đường & phương tiện công cộng",
    iconEmoji = "🏙️",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld7Nodes
  ),
  WorldData(
    id = "world_8",
    number = 8,
    chinese = "旅行",
    english = "Du Lịch",
    description = "Sân bay, đường sắt cao tốc, khách sạn & tham quan",
    iconEmoji = "✈️",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld8Nodes
  ),
  WorldData(
    id = "world_9",
    number = 9,
    chinese = "北京挑战",
    english = "Thử Thách Bắc Kinh",
    description = "Tử Cấm Thành, đỉnh Vạn Lý Trường Thành & kỳ thi Thần Long",
    iconEmoji = "🐉",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld9Nodes
  )
)

val initialAchievements = listOf(
  AchievementItem(
    id = "first_words",
    title = "Từ Vựng Đầu Tiên",
    iconLabel = "你好",
    isUnlocked = false
  ),
  AchievementItem(
    id = "first_conversation",
    title = "Hội Thoại Đầu Tiên",
    iconLabel = "💬",
    isUnlocked = false
  ),
  AchievementItem(
    id = "world_explorer",
    title = "Nhà Thám Hiểm Thế Giới",
    iconLabel = "🗺️",
    isUnlocked = false
  )
)
