package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wjn.bean.dto.BannerDto;
import com.wjn.bean.dto.HeaderDto;
import com.wjn.bean.dto.MenuDto;
import com.wjn.constant.BannerEnum;
import com.wjn.constant.HeaderEnum;
import com.wjn.constant.MenuEnum;
import com.wjn.constant.NaturalNumber;
import com.wjn.mapper.BannerMapper;
import com.wjn.mapper.HeaderMapper;
import com.wjn.mapper.MenuMapper;
import com.wjn.model.admin.Banner;
import com.wjn.model.admin.Header;
import com.wjn.model.admin.Menu;
import com.wjn.service.CommonService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

import static com.wjn.constant.GlobalConstant.TRUE;


/**
 * @auther WJN
 * @date 2019/9/30 16:49
 * @describetion
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private HeaderMapper headerMapper;

    /**
     * 通过查询数据库获取目录名称和目录的网址，然后封装给MenuDto，再添加进集合返回
     * @return list
     */
    @Override
    public List<MenuDto> getMenu() {
        /*
         * select menu_name as menus,url from menu where state = '1' order by sort asc ;
         */
        Example example = new Example(Menu.class);
        example.selectProperties(MenuEnum.menuName.name(),MenuEnum.url.name()).createCriteria().andEqualTo(MenuEnum.state.name(), NaturalNumber.one);
        example.orderBy(MenuEnum.sort.name()).asc();
        List<Menu> menus = menuMapper.selectByExample(example);
        List<MenuDto> menuDtos = new LinkedList<>();
        for (Menu menu : menus) {
            MenuDto menuDto = MenuDto.of()
                    .setMenuName(menu.getMenuName())
                    .setUrl(menu.getUrl());
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }

    /**
     * 通过通用Mapper查询，查询条件未state为1的主题，因为启用的主题只能有一个，故直接使用headers.get(0)
     * 再把Header赋值给HeaderDto，返回。
     * @return HeaderDto
     */
    @Override
    public HeaderDto getHeader() {
        Example example = new Example(Header.class);
        example.createCriteria().andEqualTo(HeaderEnum.state.name(),TRUE);
        List<Header> headers = headerMapper.selectByExample(example);
        HeaderDto headerDto = new HeaderDto();
        BeanUtil.copyProperties(headers.get(NaturalNumber.zero), headerDto);
        return headerDto;
    }

    @Override
    public BannerDto getBanner(String html) {
        Example example = new Example(Banner.class);
        example.createCriteria().andEqualTo(BannerEnum.html.name(),html);
        List<Banner> banners = bannerMapper.selectByExample(example);
        BannerDto bannerDto = BannerDto.of();
        BeanUtil.copyProperties(banners.get(NaturalNumber.zero),bannerDto);
        return bannerDto;
    }

}
