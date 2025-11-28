# 当前这个插件有点不兼容当前的swagger3.0的API接口，目前相对这个插件进行优化，可以看看这个issue投票看看后续计划
[优化建议](https://github.com/XF-FS/APIKit/issues/10)

# APIKit Pro

## 简介

APIKit Pro 是基于 [API-Security/APIKit](https://github.com/API-Security/APIKit) 的增强版本，专注于提升API安全测试体验和效率。

## 核心优势

### 🚀 智能扫描控制
- **灵活扫描开关**：精准控制扫描范围
- **自动/手动扫描模式**：满足不同安全测试需求
- **自定义扫描策略**：支持精细化API接口探测

### 🔍 多维度API识别
- 支持多种API类型：
  * REST
  * Swagger
  * GraphQL
  * SOAP
- 自动解析API文档
- 智能生成Burp测试数据包

<img width="3200" height="1770" alt="多API类型识别" src="https://github.com/user-attachments/assets/3d5c03bd-5896-48e6-8400-c3d7b27d9504" />


### 🛡️ 安全增强功能
- **Bypass扫描**：支持自定义绕过后缀
- **Cookie保留**：保持原始会话上下文
- **路径/主机过滤**：精准控制扫描范围

## 功能详解

### 扫描模式

#### 自动扫描
1. 开启 **Scanner Enabled**
2. 被动扫描访问流量中的API文档
3. **Auto Request**：自动扫描所有发现的API接口

<img width="3200" height="1704" alt="扫描开关" src="https://github.com/user-attachments/assets/b33c8696-4d3c-4985-a3bd-ec2ebf8a57a0" />


#### 手动扫描
- **Do Auto API scan**：右键请求，快速API指纹探测
- **Do Target API Scan**：
  * 配置API类型
  * 设置BasePath
  * 自定义文档路径
  * 配置Bypass策略

<img width="1827" alt="目标API扫描" src="https://github.com/user-attachments/assets/53bca247-697a-4e62-a75f-0b4eb1b5d86f" />

### 高级过滤功能

#### FilterPath 路径过滤
- 支持精确/模糊匹配
- 多关键词配置
- 被过滤路径标记为 `[FILTERED]`

#### FilterHost 主机过滤
- 灵活的主机名匹配
- 支持通配符 `*`
- 精确控制扫描范围

<img width="3200" height="580" alt="高级过滤功能" src="https://github.com/user-attachments/assets/cec6f316-e3b1-42c9-af3b-9336acc43bbb" />


### [1.6.4] - 2025-09-20

#### 新增功能
- **API详情表格新增Method列**：在API详情表格中新增"Method"列，显示HTTP请求方式（GET、POST、PUT、DELETE等）
- **智能请求方法识别**：自动从HTTP请求中提取并识别请求方法类型

#### 界面优化
- 重新调整表格列顺序，Method列位于Name列之后，便于快速识别API请求类型
- 优化表格列宽设置，确保Method列内容完整显示

#### 技术改进
- 增强`ApiDetailEntity`类，新增method字段用于存储HTTP请求方法
- 优化`PassiveScanner`中的请求解析逻辑，提高方法识别准确性
- 更新表格模型，支持8列数据显示

---

### [1.6.3]

#### 用户界面增强
- API接口列表新增序号列
- 优化内容长度显示
- 提升界面可读性

#### 性能优化
- 减少API接口重复扫描
- 提高过滤逻辑准确性
- 优化网络请求处理

---

### [1.6.2]

#### 功能改进
- 优化扫描控制逻辑
- 增强API类型识别准确性

#### 修复问题
- 修复部分场景下的扫描异常
- 优化内存使用

---

### [1.6.1]

#### 新增功能
- 支持自定义Bypass扫描后缀
- 增加Cookie保留功能
- 新增路径和主机过滤功能

#### 安全增强
- 提升API安全测试覆盖率
- 优化绕过检测机制

---

### [1.6.0]

#### 重大更新
- 基于API-Security/APIKit进行全面优化
- 重构扫描引擎，提升性能和稳定性
- 新增多种API类型支持（REST、Swagger、GraphQL、SOAP）

#### 核心功能
- 智能扫描控制
- 多维度API识别
- 自动/手动扫描模式切换
- 精细化过滤控制

#### 界面改进
- 全新的用户界面设计
- 优化的配置面板
- 增强的API详情展示

## 使用示例

### Bypass扫描示例
- `http://localhost:8089/actuator/health;.js`
- `http://localhost:8089/auth/adminlogin;.js?id=123`

## 配置参考

```
# 过滤特定路径
FilterPath: /debug/config,/admin/panel

# 过滤特定主机
FilterHost: example.com,*.test.org
```

## 兼容性
- 完全兼容 Burp Suite
- 支持多种 API 类型：REST, Swagger, GraphQL, SOAP
- 适用于各种 Web 应用安全测试场景

## 免责声明

1. 本工具仅用于授权的企业安全建设
2. 使用本工具时需确保符合当地法律法规
3. 使用本工具造成的任何非法后果由使用者承担
4. 使用本工具即视为同意本免责声明
5. 本工具仅能在取得足够合法授权的企业安全建设中使用，在使用本工具过程中，您应确保自己所有行为符合当地的法律法规。
6. 如您在使用本工具的过程中存在任何非法行为，您将自行承担所有后果，本工具所有开发者和所有贡献者不承担任何法律及连带责任。
7. 除非您已充分阅读、完全理解并接受本协议所有条款，否则，请您不要安装并使用本工具。
8. 您的使用行为或者您以其他任何明示或者默示方式表示接受本协议的，即视为您已阅读并同意本协议的约束。

## 致谢
感谢 [API-Security/APIKit](https://github.com/API-Security/APIKit) 项目的原作者。
