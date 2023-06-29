CREATE DATABASE duan1_3
go
USE duan1_3
GO

--Loai
CREATE TABLE LOAIMON(
MaLoai varchar(10) PRIMARY KEY,
TenLoai nvarchar(50) NULL
)


--Size
CREATE TABLE Size(
Ma Varchar(10) primary key,
Ten NVARCHAR(50) DEFAULT NULL
)
GO


--Nhiet do (da', am', nong') 
CREATE TABLE NhietDo(
Ma Varchar(10) primary key,
Ten NVARCHAR(50) DEFAULT NULL
)
GO


--Hat ca phe
CREATE TABLE Hat(
Ma Varchar(10) primary key,
Ten NVARCHAR(100) DEFAULT NULL
)
GO

-- Mon
	CREATE TABLE MON(
MaMon varchar(10) primary key,
TenMon nvarchar(50) NULL,
MaLoai_FK varchar(10),
MaNhietDo varchar(10),
MaSize varchar(10),
MaHat varchar(10),
NguyenLieu NVARCHAR(200),
Gia Int DEFAULT NULL,
Anh NVARCHAR(50) DEFAULT NULL,
Mota NVARCHAR(Max) DEFAULT NULL,
TinhTrang INT DEFAULT 1 /*1: hoạt đông, 0: k hoạt động*/
)
GO

-- Ban
CREATE TABLE BAN(
MaBan varchar(10) PRIMARY KEY,
TenBan NVARCHAR(50)  NULL,
MaKV_FK varchar(10)  NULL,
SoNguoi varchar(10)	null,
TinhTrang nvarchar(20) null,
TrangThai nvarchar(20) null,
KhachHang nvarchar(50) null
)
GO

-- Khu Vuc
CREATE TABLE KHUVUC(
MaKV varchar(10) PRIMARY KEY,
TenKV nvarchar(50) NULL,
)
GO
-- nhân viên
CREATE TABLE NHANVIEN (

  MaNV varchar(10) PRIMARY KEY,
  pass nvarchar(50) null,
  TenNV nvarchar(50) null,
  Hinh nvarchar(50)DEFAULT null,
  CMND nvarchar(20) null,
  Diachi nvarchar(100) null,
  DienThoai nvarchar(15) null,
  GioiTinh int Default 0,--0 là nam 1 là nữ
  NgaySinh date null,
  MaCV_FK varchar(10),
  TinhTrang INT DEFAULT 0 -- 0 là đang đang làm/ 1 tạm nghỉ, 2 nghỉ rồi
)
GO

-- chức vụ

CREATE TABLE ChucVu (
  MaCV varchar(10) PRIMARY KEY,
  TenCV NVARCHAR(50) DEFAULT NULL
)
GO


--Khachhang
CREATE TABLE KHACHHANG(
	MaKH     varchar(10) PRIMARY KEY,
	TenKh    NVARCHAR(30) NULL,
	SDT NVARCHAR(10) NULL,
	ngaySinh      NVARCHAR(15) NULL,
	email   NVARCHAR(50) NULL,
)
Go

--HoaDon
/*CREATE TABLE HOADON(
MaHD varchar(10) PRIMARY KEY ,
	MaBan_FK varchar(10) NULL,
	MaNV_FK varchar(10) NULL,

	NgayHD date NULL,
	Tongtien float NULL,
	Trangthai bit NULL,
)
GO*/
 ---drop table HOADON

CREATE TABLE HOADON(
MaHD varchar(10) PRIMARY KEY ,
	MaBan_FK varchar(10) NULL,
	MaNV_FK varchar(10) NULL,
	MaKH_FK varchar(10) NULL,
	NgayHD date NULL,
	Tongtien float NULL,
	Trangthai bit NULL,
)
GO
-- HOA DON CT
CREATE TABLE CTHOADON(
MaCTHD varchar(30) PRIMARY KEY ,
MaHD_FK varchar(10) NULL,
MaMon_FK varchar(10) NULL,
SoLuong int NULL,
DonGia float NULL,
)
GO

/*Add quan he*/

--HoaDon - Ban
ALTER TABLE HOADON ADD FOREIGN KEY (MaBan_FK) REFERENCES BAN(MaBan)

-- HoaDon - KhachHang
ALTER TABLE HOADON ADD FOREIGN KEY (MaKH_FK) REFERENCES KHACHHANG(MAKH)

--HoaDon - NhanVien
ALTER TABLE HOADON ADD FOREIGN KEY (MaNV_FK) REFERENCES NHANVIEN(MaNV)

--Ban - KhuVuc
ALTER TABLE BAN ADD FOREIGN KEY (MaKV_FK) REFERENCES KHUVUC(MaKV)

-- Mon - LoaiMon
ALTER TABLE Mon ADD FOREIGN KEY(MaLoai_FK) REFERENCES LOAIMON(MaLoai)

-- Mon - NHietDo
ALTER TABLE Mon ADD FOREIGN KEY(MaNhietDo) REFERENCES NhietDo(Ma)

-- Mon - Size
ALTER TABLE Mon ADD FOREIGN KEY(MaSize) REFERENCES Size(Ma)

-- Mon - Hat
ALTER TABLE Mon ADD FOREIGN KEY(MaHat) REFERENCES Hat(Ma)

-- CTHD - Mon
ALTER TABLE CTHOADON ADD FOREIGN KEY(MaMon_FK) REFERENCES MON(MaMon)

-- CTHD - HoaDon
ALTER TABLE CTHOADON ADD FOREIGN KEY(MaHD_FK) REFERENCES HOADON(MaHD)
-- Nhan vien- chức vụ
 ALTER TABLE NHANVIEN ADD FOREIGN KEY (MaCV_FK) REFERENCES ChucVu (MaCV)


 /*Insert data*/

--Loại món
INSERT INTO LOAIMON(MaLoai,TenLoai) VALUES
('CFVN', N'Cà phê Việt Nam'),
('CFM', N'Cà phê máy'),
('CB', N'Cold Brew')

--Nhiệt độ
INSERT INTO NhietDo(Ma, Ten) VALUES
('DA', N'Đá'),
('NONG', N'Nóng'),
('AM', N'Ấm')

--Size
INSERT INTO Size(Ma, Ten) VALUES
('S', N'Nhỏ'),
('M', N'Vừa'),
('L', N'Lớn')

--Hat
INSERT INTO Hat(Ma, Ten) VALUES
('H1', N'Arabica'),
('H2', N'Robusta'),
('H3', N'Colombia'),
('H4', N'Ethiopia'),
('H5', N'Brazil'),
('H6', N'blend Arabica-Robusta'),
('H7', N'blend Arabica-Colombia'),
('H8', N'blend Brazil -Ethiopia')

--Mon
insert into Mon(MaMon, TenMon, MaLoai_FK, MaNhietDo, MaSize,MaHat , NguyenLieu, Gia, Anh, Mota, TinhTrang) values
('SP1','Coldbrew truyền thống','CB','DA','M','H1','sang xịn mịn','35000','cb_truyen_thong.jpg','ngon',1),
('SP2','Cà phê sữa đá','CFM','DA','L','H3','sữa, cà phê','30000','sua_da.jpg','ngon',1)

--Khu vuc
INSERT [dbo].[KHUVUC] ([MaKV], [TenKV]) VALUES (N'KV1205443 ', N'Lầu 1')
INSERT [dbo].[KHUVUC] ([MaKV], [TenKV]) VALUES (N'KV3212527 ', N'Lầu 2')

--Ban
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'14A181627 ', N'Bàn 01', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'14A183535 ', N'Bàn 02', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'15A183536 ', N'Bàn 03', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'15A183537 ', N'Bàn 04', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'16A183538 ', N'Bàn 05', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'17A183539 ', N'Bàn 06', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'18A183540 ', N'Bàn 07', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'18A183541 ', N'Bàn 08', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'18A21112  ', N'Bàn 09', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'19A181628 ', N'Bàn 10', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'19A183542 ', N'Bàn 11', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'20A183543 ', N'Bàn 12', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'21A181629 ', N'Bàn 13', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'25A123519 ', N'Bàn 14', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'10A105312 ', N'Bàn 15', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'10A181125 ', N'Bàn 16', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'10A21111  ', N'Bàn 17', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'12A183534 ', N'Bàn 18', N'KV1205443 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'6A181124  ', N'Bàn 19', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'3A21139   ', N'Bàn 20', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'41A183430 ', N'Bàn 21', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'42A183431 ', N'Bàn 22', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'43A123116 ', N'Bàn 23', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'43A183432 ', N'Bàn 24', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'44A21116  ', N'Bàn 25', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'45A181022 ', N'Bàn 26', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'46A183433 ', N'Bàn 27', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'48A123117 ', N'Bàn 28', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'51A105414 ', N'Bàn 29', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'51A21117  ', N'Bàn 30', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'55A105415 ', N'Bàn 31', N'KV3212527 ')
INSERT [dbo].[BAN] ([MaBan], [TenBan], [MaKV_FK]) VALUES (N'57A123418 ', N'Bàn 32', N'KV3212527 ')
GO

select * from NHANVIEN
SELECT MaNV FROM NhanVien WHERE MaNV = 'admin';
--Nhanvien
INSERT INTO [dbo].[NHANVIEN] ([MaNV], [pass], [TenNV], [Hinh], [CMND], [Diachi], [DienThoai], [NgaySinh], [GioiTinh], [TinhTrang], [MaCV_FK])
VALUES 
  ('admin', 'admin', N'admin', 'avatar.jpg', '123456789', N'123 Trần Hưng Đạo', '1234567890', '1990-01-01', 0, 0, 'CV01'),
  ('NV002', '123', N'Trần Xuân Duy', 'avatar3.jpg', '987654321', N'456 Khuất Duy Tiến', '0987654321', '1992-05-10', 0, 1, 'CV04'),
  ('NV003', '234', N'Nguyễn Trọng Minh Hiếu', 'avatar_6.jpg', '654321789', N'500 Đống đa', '5678901234', '1985-09-20', 0, 0, 'CV03'),
  ('NV004', '111', N'Nami', 'avatar_2.png', '321987654', N'234 Lạng Hạ', '4321098765', '1991-12-15', 1, 0, 'CV02'),
  ('NV005', '555', N'Ran', 'avatar_4.png', '789654321', N'222 Nguyễn Xiến', '2109876543', '1993-07-25', 1, 1, 'CV01');



-- CHUC VU
INSERT [dbo].[ChucVu] ([MaCV],[TenCV]) VALUES 
('CV01',N'Quản lý'),
('CV02',N'Phục Vụ'),
('CV03',N'Thu ngân'),
('CV04',N'Pha chế')

select * from ChucVu


select * from NHANVIEN
s