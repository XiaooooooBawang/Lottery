create
    database lottery;

-- auto-generated definition
create table activity
(
    id              bigint auto_increment comment '自增ID'
        primary key,
    activity_id     bigint                             null comment '活动ID',
    activity_name   varchar(64)                        not null comment '活动名称',
    activity_desc   varchar(128)                       null comment '活动描述',
    begin_date_time datetime                           not null comment '开始时间',
    end_date_time   datetime                           not null comment '结束时间',
    stock_count     int                                not null comment '库存',
    take_count      int                                null comment '每人可参与次数',
    activity_state  int                                null comment '活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启',
    creator         varchar(64)                        not null comment '创建人',
    create_time     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     datetime default CURRENT_TIMESTAMP not null comment '修改时间',
    constraint activity_id_uindex
        unique (id)
) comment '活动配置';


-- auto-generated definition
create table award
(
    id            bigint(11) auto_increment comment '自增ID'
        primary key,
    award_id      bigint                             null comment '奖品ID',
    award_type    int(4)                             null comment '奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）',
    award_count   int                                null comment '奖品数量',
    award_name    varchar(64)                        null comment '奖品名称',
    award_content varchar(128)                       null comment '奖品内容「文字描述、Key、码」',
    create_time   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP not null comment 'updateTime'
) comment '奖品配置';

-- auto-generated definition
create table strategy
(
    id            bigint(11) auto_increment comment '自增ID'
        primary key,
    strategy_id   bigint(11)                         not null comment '策略ID',
    strategy_desc varchar(128)                       null comment '策略描述',
    strategy_mode int(4)                             null comment '策略方式「1:单项概率、2:总体概率」',
    grant_type    int(4)                             null comment '发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」',
    grant_date    datetime                           null comment '发放奖品时间',
    ext_info      varchar(128)                       null comment '扩展信息',
    create_time   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP not null comment '修改时间',
    constraint strategy_strategy_id_uindex
        unique (strategy_id)
) comment '策略配置';

-- auto-generated definition
create table strategy_detail
(
    id          bigint(11) auto_increment comment '自增ID'
        primary key,
    strategy_id bigint(11)                         not null comment '策略ID',
    award_id    bigint(11)                         null comment '奖品ID',
    award_count int                                null comment '奖品数量',
    award_rate  decimal(5, 2)                      null comment '中奖概率',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '修改时间'
) comment '策略明细';
