-- -------------------------------------------------------------
-- TablePlus 4.7.1(428)
--
-- https://tableplus.com/
--
-- Database: questions.db
-- Generation Time: 2022-06-20 14:23:01.9730
-- -------------------------------------------------------------


CREATE TABLE "questions" ("id" integer NOT NULL DEFAULT '',"txt" text DEFAULT '',"ans1" text DEFAULT '',"ans2" text DEFAULT '',"ans3" text DEFAULT '',"ans4" text DEFAULT '',"right" integer NOT NULL DEFAULT '',"lvl" integer NOT NULL DEFAULT '', PRIMARY KEY (id));

INSERT INTO "questions" ("id", "txt", "ans1", "ans2", "ans3", "ans4", "right", "lvl") VALUES
('1', 'love pizza', 'yes', 'of course', 'absolutely', 'probably', '2', '3');
