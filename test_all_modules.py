
import requests

BASE_URL = 'http://localhost:8080'
LOGIN_URL = f'{BASE_URL}/api/auth/login'

CLASS_URL = f'{BASE_URL}/api/class'
COURSE_URL = f'{BASE_URL}/api/course'
ASSIGNMENT_URL = f'{BASE_URL}/api/assignment'
STATS_URL = f'{BASE_URL}/api/statistics/submission-rate'
UPLOAD_URL = f'{BASE_URL}/api/upload'
DOWNLOAD_PREFIX = f'{BASE_URL}/api/upload/files/'
LOG_URL = f'{BASE_URL}/api/log/list'  # 可根据实际路径修改

COURSE_ID = 1  # 默认测试课程ID
TEST_FILE = 'test_report.pdf'

def login():
    print('== 登录 ==')
    r = requests.post(LOGIN_URL, json={'username': 'admin', 'password': '123456'})
    assert r.status_code == 200
    token = r.json()['data']['token']
    print('✅ 登录成功')
    return token

def test_class(token):
    h = {'Authorization': f'Bearer {token}'}
    print('\n== 班级管理 ==')
    r = requests.post(CLASS_URL + '/add', json={
        'classCode': 'TEST001',
        'className': '测试班级',
        'major': '软件工程',
        'grade': '2025级'
    }, headers=h)
    assert r.status_code == 200
    print('✅ 添加班级')

def test_course(token):
    h = {'Authorization': f'Bearer {token}'}
    print('\n== 课程管理 ==')
    r = requests.post(COURSE_URL + '/add', json={
        'courseCode': 'TC2025',
        'courseName': '测试课程',
        'teacherName': '张老师',
        'experimentCount': 3,
        'deadline': '2025-12-31T23:59:59'
    }, headers=h)
    assert r.status_code == 200
    print('✅ 添加课程')

def test_assignment(token):
    h = {'Authorization': f'Bearer {token}'}
    print('\n== 实验/作业管理 ==')
    r = requests.post(ASSIGNMENT_URL + '/add', json={
        'courseId': COURSE_ID,
        'title': '测试实验1',
        'description': '内容说明',
        'experimentNumber': 1,
        'deadline': '2025-12-30T23:59:59'
    }, headers=h)
    assert r.status_code == 200
    print('✅ 添加实验')

def test_statistics(token):
    h = {'Authorization': f'Bearer {token}'}
    print('\n== 提交率统计 ==')
    r = requests.get(f'{STATS_URL}/{COURSE_ID}', headers=h)
    assert r.status_code == 200
    print('✅ 提交率数据获取，共', len(r.json()['data']), '条')

def test_upload(token):
    h = {'Authorization': f'Bearer {token}'}
    print('\n== 文件上传测试 ==')
    with open(TEST_FILE, 'rb') as f:
        files = {'file': (TEST_FILE, f)}
        r = requests.post(UPLOAD_URL, files=files, headers=h)
    assert r.status_code == 200
    file_path = r.json()['data']
    print('✅ 上传成功，路径：', file_path)

    # 下载测试
    r = requests.get(BASE_URL + file_path)
    assert r.status_code == 200
    with open('downloaded_' + TEST_FILE, 'wb') as f:
        f.write(r.content)
    print('✅ 下载成功')

def test_log(token):
    h = {'Authorization': f'Bearer {token}'}
    print('\n== 日志系统测试 ==')
    r = requests.get(LOG_URL, headers=h)
    if r.status_code == 200:
        print('✅ 日志查询成功，条数:', len(r.json()['data']))
    else:
        print('⚠️ 日志接口不可用，响应码:', r.status_code)

if __name__ == '__main__':
    try:
        token = login()
        test_class(token)
        test_course(token)
        test_assignment(token)
        test_statistics(token)
        test_upload(token)
        test_log(token)
        print('\n✅ 全部功能模块测试完成')
    except AssertionError:
        print('\n❌ 测试断言失败')
    except Exception as e:
        print('\n❌ 运行异常:', e)
