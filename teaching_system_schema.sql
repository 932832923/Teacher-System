
-- 班级信息表
CREATE TABLE class_info (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '班级ID',
  class_code VARCHAR(20) NOT NULL UNIQUE COMMENT '班级编号',
  class_name VARCHAR(50) NOT NULL COMMENT '班级名称',
  major VARCHAR(100) NOT NULL COMMENT '专业',
  grade VARCHAR(20) NOT NULL COMMENT '年级（如2022级）',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='班级信息表';

-- 学生信息表
CREATE TABLE student_info (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '学生ID',
  student_no VARCHAR(20) NOT NULL UNIQUE COMMENT '学号',
  name VARCHAR(50) NOT NULL COMMENT '学生姓名',
  gender ENUM('男','女') DEFAULT '男' COMMENT '性别',
  class_id BIGINT NOT NULL COMMENT '所属班级ID',
  grade VARCHAR(20) NOT NULL COMMENT '年级',
  major VARCHAR(100) NOT NULL COMMENT '专业',
  email VARCHAR(100) DEFAULT NULL COMMENT '邮箱（可选）',
  phone VARCHAR(20) DEFAULT NULL COMMENT '联系电话（可选）',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (class_id) REFERENCES class_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
  INDEX idx_class_id (class_id),
  INDEX idx_student_no (student_no)
) COMMENT='学生信息表';

-- 课程信息表
CREATE TABLE course_info (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
  course_code VARCHAR(20) NOT NULL UNIQUE COMMENT '课程编号',
  course_name VARCHAR(100) NOT NULL COMMENT '课程名称',
  teacher_name VARCHAR(50) NOT NULL COMMENT '授课教师姓名',
  experiment_count INT DEFAULT 0 COMMENT '实验数量',
  deadline DATETIME DEFAULT NULL COMMENT '课程默认截止时间（可选）',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='课程信息表';

-- 课程与班级关联表
CREATE TABLE course_class_relation (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  course_id BIGINT NOT NULL COMMENT '课程ID',
  class_id BIGINT NOT NULL COMMENT '班级ID',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
  FOREIGN KEY (course_id) REFERENCES course_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (class_id) REFERENCES class_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
  UNIQUE KEY uk_course_class (course_id, class_id),
  INDEX idx_course_id (course_id),
  INDEX idx_class_id (class_id)
) COMMENT='课程与班级关联表';

-- 实验/作业信息表
CREATE TABLE assignment_info (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '作业ID',
  course_id BIGINT NOT NULL COMMENT '所属课程ID',
  title VARCHAR(100) NOT NULL COMMENT '实验/作业标题',
  description TEXT COMMENT '作业描述',
  deadline DATETIME COMMENT '提交截止时间',
  experiment_number INT COMMENT '实验编号（如第几次实验）',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (course_id) REFERENCES course_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
  INDEX idx_course_id (course_id)
) COMMENT='实验/作业信息表';

-- 实验提交记录表
CREATE TABLE submission_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '提交记录ID',
  assignment_id BIGINT NOT NULL COMMENT '对应作业ID',
  student_id BIGINT NOT NULL COMMENT '提交人ID',
  file_url VARCHAR(255) COMMENT '提交的文件地址',
  status ENUM('未提交','已提交','已批改') DEFAULT '已提交' COMMENT '提交状态',
  score DECIMAL(5,2) DEFAULT NULL COMMENT '评分（可选）',
  comment TEXT COMMENT '教师评语（可选）',
  submit_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (assignment_id) REFERENCES assignment_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (student_id) REFERENCES student_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
  UNIQUE KEY uk_assignment_student (assignment_id, student_id),
  INDEX idx_assignment_id (assignment_id),
  INDEX idx_student_id (student_id)
) COMMENT='实验提交记录表';

-- 系统操作日志表
CREATE TABLE audit_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '日志ID',
  operator_id BIGINT NOT NULL COMMENT '操作者ID（教师）',
  operator_name VARCHAR(50) NOT NULL COMMENT '操作者姓名',
  operation_type ENUM('新增','修改','删除','查询','导入','导出','登录','其他') NOT NULL COMMENT '操作类型',
  target_module VARCHAR(100) NOT NULL COMMENT '操作模块（如班级管理、课程管理）',
  operation_detail TEXT COMMENT '具体操作内容描述',
  request_ip VARCHAR(50) COMMENT '请求IP地址',
  user_agent TEXT COMMENT '浏览器信息',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  INDEX idx_operator_id (operator_id),
  INDEX idx_module_time (target_module, create_time)
) COMMENT='系统操作日志表';
